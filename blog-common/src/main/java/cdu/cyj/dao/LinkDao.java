package cdu.cyj.dao;

import cdu.cyj.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Link)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-27 09:54:35
 */
@Mapper
public interface LinkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Link queryById(Integer id);

    /**
     * 统计总行数
     *
     * @param link 查询条件
     * @return 总行数
     */
    long count(Link link);

    /**
     * 新增数据
     *
     * @param link 实例对象
     * @return 影响行数
     */
    int insert(Link link);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Link> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Link> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Link> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Link> entities);

    /**
     * 修改数据
     *
     * @param link 实例对象
     * @return 影响行数
     */
    int update(Link link);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过状态码查询link列表
     *
     * @return 对象列表
     */
    List<Link> queryAllByStatus(Integer status);

    /**
     * 通过对象查询
     *
     * @return 对象列表
     */
    List<Link> queryAllByObject(Link link);

    /**
     * 批量删除
     *
     * @param ids id列表
     * @return 影响行数
     */
    int deleteByIdBatch(@Param("ids") List<Integer> ids);
}

