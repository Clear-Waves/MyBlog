package cdu.cyj.service.impl;

import cdu.cyj.dao.RoleDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.RoleAddDto;
import cdu.cyj.domain.dto.RoleUpdateDto;
import cdu.cyj.domain.entity.Role;
import cdu.cyj.domain.vo.AdminRoleDetailVo;
import cdu.cyj.domain.vo.AdminRoleListVo;
import cdu.cyj.domain.vo.AdminRoleOptionListVo;
import cdu.cyj.domain.vo.PageVo;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.service.RoleService;
import cdu.cyj.utils.AutoFilledUtils;
import cdu.cyj.utils.BeanCopyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public ResponseResult<?> listAllRole() {

        // 调用dao查询
        List<Role> roleList = roleDao.queryAll();
        // 封装返回
        List<AdminRoleOptionListVo> adminRoleOptionListVos = BeanCopyUtils.copyBeanList(roleList, AdminRoleOptionListVo.class);

        return ResponseResult.okResult(adminRoleOptionListVos);
    }

    @Override
    public ResponseResult<?> listRole(Role role, Integer pageNum, Integer pageSize) {

        // 分页
        PageHelper.startPage(pageNum, pageSize);
        // 调用dao查询
        List<Role> roles = roleDao.queryALlByObject(role);
        // 封装返回
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        List<AdminRoleListVo> roleVo = BeanCopyUtils.copyBeanList(roles, AdminRoleListVo.class);
        PageVo pageVo = new PageVo(roleVo, rolePageInfo.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> getRole(Integer id) {

        // 调用dao查询
        Role role = roleDao.queryById(id);
        // 封装返回
        AdminRoleDetailVo adminRoleDetailVo = BeanCopyUtils.copyBean(role, AdminRoleDetailVo.class);
        return ResponseResult.okResult(adminRoleDetailVo);

    }

    @Transactional
    @Override
    public ResponseResult<?> updateRole(RoleUpdateDto roleUpdateDto) {

        // 解析参数
        List<Integer> menuIds = roleUpdateDto.getMenuIds();
        Role role = BeanCopyUtils.copyBean(roleUpdateDto, Role.class);
        // 自动填充
        AutoFilledUtils.autoFillOnUpdate(role);
        // 调用dao更新角色表
        int countUpdate = roleDao.update(role);
        // 调用dao跟新角色菜单中间表
        // 先删除
        roleDao.deleteRoleMenuByRoleId(role.getId());
        // 后添加
        int countRoleMenu = 0;
        if (menuIds != null && menuIds.size() > 0) {
            countRoleMenu = roleDao.insertRoleMenuBatch(role.getId(), menuIds);
        }
        // 封装返回
        if (countUpdate == 1 &&  (menuIds == null || countRoleMenu == menuIds.size())) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseResult<?> addRole(RoleAddDto roleAddDto) {
        // 解析参数
        List<Integer> menuIds = roleAddDto.getMenuIds();
        Role role = BeanCopyUtils.copyBean(roleAddDto, Role.class);
        // 自动填充
        AutoFilledUtils.autoFillOnInsert(role);
        // 调用dao插入角色表
        int insert = roleDao.insert(role);
        // 调用dao添加角色菜单中间表
        int insertRoleMenuBatch = 0;
        if (menuIds != null && menuIds.size() > 0) {
            insertRoleMenuBatch = roleDao.insertRoleMenuBatch(role.getId(), menuIds);
        }
        // 封装返回
        if (insert == 1 && (menuIds == null || insertRoleMenuBatch == menuIds.size())) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> changeRoleStatus(Map<String, Integer> map) {
        // 参数验证
        if (map.get("roleId") == null || map.get("status") == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_ERROR);
        }
        // 解析参数
        Role role = new Role();
        role.setId(map.get("roleId"));
        role.setStatus(map.get("status"));
        // 自动填充
        AutoFilledUtils.autoFillOnUpdate(role);
        // 调用dao更新
        int update = roleDao.update(role);
        // 封装返回
        if (update == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseResult<?> deleteRole(List<Integer> roleIds) {
        // 调用dao删除角色表
        int delete = roleDao.deleteByIdBatch(roleIds);
        // 调用dao删除角色-菜单表
        roleDao.deleteRoleMenuByRoleIdBatch(roleIds);
        // 封装返回
        if (delete == roleIds.size()) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }
}
