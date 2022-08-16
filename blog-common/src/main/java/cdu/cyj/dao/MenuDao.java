package cdu.cyj.dao;

import cdu.cyj.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-07 10:15:59
 */
@Repository
public interface MenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Menu queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param menu 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Menu> queryAllByLimit(Menu menu, @Param("pageable") Pageable pageable);

    /**
     * 获取指定条件的记录
     *
     * @param status 状态码
     * @param visible 是否可见
     * @param parent 父菜单
     * @return 对象列表
     */
    List<Menu> queryAllByStatusAndVisibleAndParent(@Param("status") Integer status, @Param("visible") Integer visible, @Param("parent")Integer parent);

    /**
     * 统计总行数
     *
     * @param menu 查询条件
     * @return 总行数
     */
    long count(Menu menu);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Menu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Menu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Menu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Menu> entities);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过角色id查询对应的菜单id
     *
     * @param rid 角色id
     * @return 菜单id列表
     */
    List<Integer> queryAllByRoleId(Integer rid);

    /**
     * 通过角色id列表查询对应的菜单id
     *
     * @param roleIds 角色id列表
     * @return 菜单id列表
     */
    List<Integer> queryIdByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<Menu> queryByIdBatch(List<Integer> menuIds);

    /**
     * 通过状态查询对应权限
     *
     * @param status 状态
     * @return 菜单列表
     */
    List<Menu> queryAllByStatus(Integer status);
}

