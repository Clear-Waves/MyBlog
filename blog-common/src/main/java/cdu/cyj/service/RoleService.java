package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.RoleAddDto;
import cdu.cyj.domain.dto.RoleUpdateDto;
import cdu.cyj.domain.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    /**
     * 查询所有角色列表
     *
     * @return 统一返回对象
     */
    ResponseResult<?> listAllRole();

    /**
     * 按条件插叙角色列表
     *
     * @param role 角色对象
     * @return 统一返回
     */
    ResponseResult<?> listRole(Role role, Integer pageNum, Integer pageSize);

    /**
     * 通过id查询角色详情
     *
     * @param id 角色id
     * @return 统一返回对象
     */
    ResponseResult<?> getRole(Integer id);

    /**
     * 更新角色信息
     *
     * @param roleUpdateDto 角色更新dto
     * @return 统一返回对象
     */
    ResponseResult<?> updateRole(RoleUpdateDto roleUpdateDto);

    /**
     * 添加角色信息
     *
     * @param roleAddDto 角色添加DTO
     * @return 统一返回对象
     */
    ResponseResult<?> addRole(RoleAddDto roleAddDto);

    /**
     * 改变角色状态
     *
     * @param map 参数map
     * @return 统一返回对象
     */
    ResponseResult<?> changeRoleStatus(Map<String, Integer> map);

    /**
     * 删除多个角色
     *
     * @param roleIds 角色id列表
     * @return 统一返回对象
     */
    ResponseResult<?> deleteRole(List<Integer> roleIds);
}
