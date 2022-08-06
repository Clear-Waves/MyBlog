package cdu.cyj.service.impl;

import cdu.cyj.dao.UserDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.domain.vo.AdminUserInfoVo;
import cdu.cyj.domain.vo.UserInfoVo;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.exception.SystemException;
import cdu.cyj.service.UserService;
import cdu.cyj.utils.AutoFilledUtils;
import cdu.cyj.utils.BeanCopyUtils;
import cdu.cyj.utils.SecurityUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

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
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        map.put("user", adminUserInfoVo);
        map.put("roles", roles);
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
}
