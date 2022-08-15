package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;

public interface MenuService {
    ResponseResult<?> getRouters();

    /**
     * 获取菜单树形结构
     *
     * @return 统一返回对象
     */
    ResponseResult<?> getTree();

    /**
     * 通过角色id查询菜单列表
     *
     * @param rid 角色id
     * @return 统一返回对象
     */
    ResponseResult<?> getTreeByRoleId(Integer rid);
}
