package cdu.cyj.dao;

import cdu.cyj.domain.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-13 15:36:46
 */
@Repository
public interface RoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param role 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Role> queryAllByLimit(Role role, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param role 查询条件
     * @return 总行数
     */
    long count(Role role);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int insert(Role role);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Role> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Role> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Role> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Role> entities);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询所有记录
     *
     * @return 角色列表
     */
    List<Role> queryAll();

    /**
     * 通过实体查询所有列
     *
     * @param role 角色实体
     * @return 角色列表
     */
    List<Role> queryALlByObject(Role role);

    /**
     * 更新角色菜单中间表
     *
     * @param rid 角色id
     * @param menuIds 菜单id列表
     * @return 受影响行数
     */
    int updateRoleMenu(Integer rid, List<Integer> menuIds);

    /**
     * 根据角色批量删除
     *
     * @param rid 角色id
     * @return 受影响行数
     */
    int deleteRoleMenuByRoleId(Integer rid);

    /**
     * 批量插入角色-菜单中间表
     *
     * @param rid 角色id
     * @param menuIds 菜单id列表
     * @return 受影响行数
     */
    int insertRoleMenuBatch(@Param("rid") Integer rid, @Param("menuIds")List<Integer> menuIds);

    /**
     * 通过id list 批量删除
     * @return 受影响行
     */
    int deleteByIdBatch(@Param("roleIds") List<Integer> roleIds);

    /**
     * 批量删除 角色-菜单中甲表
     *
     * @param roleIds 角色id列表
     * @return 受影响行
     */
    int deleteRoleMenuByRoleIdBatch(@Param("roleIds") List<Integer> roleIds);

    /**
     * 通过用户id查询角色id列表
     *
     * @param userId 用户id
     * @return 角色id列表
     */
    List<Integer> queryIdsByUserId(Integer userId);

    List<Role> queryAllByIds(@Param("ids") List<Integer> ids);
}

