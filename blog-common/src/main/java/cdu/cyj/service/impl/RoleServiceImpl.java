package cdu.cyj.service.impl;

import cdu.cyj.dao.RoleDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Role;
import cdu.cyj.domain.vo.AdminRoleOptionListVo;
import cdu.cyj.service.RoleService;
import cdu.cyj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
