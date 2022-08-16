package cdu.cyj.service.impl;

import cdu.cyj.dao.RoleDao;
import cdu.cyj.dao.UserDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.UserAddDto;
import cdu.cyj.domain.dto.UserUpdateDto;
import cdu.cyj.domain.entity.Role;
import cdu.cyj.domain.entity.User;
import cdu.cyj.domain.vo.*;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.exception.SystemException;
import cdu.cyj.service.UserService;
import cdu.cyj.utils.AutoFilledUtils;
import cdu.cyj.utils.BeanCopyUtils;
import cdu.cyj.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleDao roleDao;

    @Override
    public ResponseResult<?> userInfo() {

        // 获取、查询
        Integer userId = SecurityUtils.getUserId();
        User user = userDao.queryById(userId);

        // 封装
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult<?> adminUserInfo() {

        // 获取、查询
        Integer userId = SecurityUtils.getUserId();
        User user = userDao.queryById(userId);

        // 封装
        AdminUserInfoVo adminUserInfoVo = BeanCopyUtils.copyBean(user, AdminUserInfoVo.class);
        Map<String, Object> map = new HashMap<>();
        List<Integer> ids = roleDao.queryIdsByUserId(userId);
        List<Role> roleList = roleDao.queryAllByIds(ids);
        map.put("user", adminUserInfoVo);
        map.put("roles", roleList);
        map.put("permissions", null);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult<?> updateUserInfo(User user) {

        // 自动填充更新时间
        AutoFilledUtils.autoFillOnUpdate(user);
        // 调用dao更新
        int count = userDao.update(user);
        // 返回结果
        if (count == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> register(User user) {

        // 参数验证
        if (StringUtil.isNullOrEmpty(user.getUserName()) || StringUtil.isNullOrEmpty(user.getNickName()) || StringUtil.isNullOrEmpty(user.getEmail()) || StringUtil.isNullOrEmpty(user.getPassWord())) {
            throw new SystemException(AppHttpCodeEnum.PARAMETER_ERROR);
        }

        // TODO 判断密码是否合规

        // 查询用户名/邮箱是否重复
        if (userDao.userNameExist(user.getUserName()) != null) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        if (userDao.emailExist(user.getEmail()) != null) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        if (userDao.nickNameExist(user.getNickName()) != null) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        // 密码加密存储
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        // 自动填充
        AutoFilledUtils.autoFillOnInsert(user);
        // 写入数据库
        int count = userDao.insert(user);

        // 封装返回值
        if (count == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.ADD_ERROR);
        }
    }

    @Override
    public ResponseResult<?> listUser(User user, Integer pageNum, Integer pageSize) {
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        // 调用dao查询
        List<User> users = userDao.queryByObject(user);
        // 封装返回
        List<AdminUserListVo> adminUserListVos = BeanCopyUtils.copyBeanList(users, AdminUserListVo.class);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        PageVo pageVo = new PageVo(adminUserListVos, userPageInfo.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> userDetail(Integer id) {
        // 调用dao插叙
        User user = userDao.queryById(id);
        // 调用roleDao查询角色数据
        List<Role> roleList = roleDao.queryAll();
        List<AdminRoleOptionListVo> adminRoleOptionListVos = BeanCopyUtils.copyBeanList(roleList, AdminRoleOptionListVo.class);
        // 查询该用户的角色id
        List<Integer> ids = userDao.queryRoleIdsByUserId(id);
        // 封装返回
        AdminUserDetailVo adminUserDetailVo = BeanCopyUtils.copyBean(user, AdminUserDetailVo.class);

        // 封装
        AdminUserUpdateVo adminUserUpdateVo = new AdminUserUpdateVo(adminUserDetailVo, adminRoleOptionListVos, ids);
        return ResponseResult.okResult(adminUserUpdateVo);
    }

    @Transactional
    @Override
    public ResponseResult<?> addUser(UserAddDto userAddDto) {

        // 类转换
        User user = BeanCopyUtils.copyBean(userAddDto, User.class);
        // 自动填充
        AutoFilledUtils.autoFillOnInsert(user);
        // 密码加密
        String encodePassword = passwordEncoder.encode(user.getPassWord());
        user.setPassWord(encodePassword);
        /*
           调用dao进行插入：
              1. 插入user表
              2. 插入user-role中间表
        */
        int insertUser = userDao.insert(user);
        int insertUserRole = userDao.insertUserRoleBatch(user.getId(), userAddDto.getRoleIds());

        // 封装返回
        if (insertUser == 1 && insertUserRole == userAddDto.getRoleIds().size()) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseResult<?> adminUpdateUser(UserUpdateDto userUpdateDto) {

        // 类转换
        User user = BeanCopyUtils.copyBean(userUpdateDto, User.class);
        // 自动填充
        AutoFilledUtils.autoFillOnUpdate(user);
        // 删除用户角色对应信息
        userDao.deleteUserRoleByUserId(user.getId());
        // 添加用户角色对应信息
        Integer countRole = userDao.insertUserRoleBatch(user.getId(), userUpdateDto.getRoleIds());
        // 更新用户信息
        int countUser = userDao.update(user);
        // 封装返回
        if (countRole == userUpdateDto.getRoleIds().size() && countUser == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> changeStatus(Integer userId, Integer status) {
        // 检查参数
        if (userId == null || status == null || status < 0 || status >1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_ERROR);
        }
        // 封装类
        User user = new User();
        user.setStatus(status);
        user.setId(userId);
        // 自动填充
        AutoFilledUtils.autoFillOnUpdate(user);
        // 调用dao进行更新
        int update = userDao.update(user);
        // 封装返回
        if (update == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseResult<?> deleteUser(List<Integer> userIds) {
        // 调用dao删除用户记录
        int count = userDao.deleteByIdBatch(userIds);
        // 调用dao删除用户-角色记录
        userDao.deleteUserRoleByUserIdBatch(userIds);
        // 封装返回
        if (count == userIds.size()) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }


}
