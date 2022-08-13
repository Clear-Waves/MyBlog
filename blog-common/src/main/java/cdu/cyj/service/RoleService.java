package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;

public interface RoleService {

    /**
     * 查询所有角色列表
     *
     * @return 统一返回对象
     */
    ResponseResult<?> listAllRole();
}
