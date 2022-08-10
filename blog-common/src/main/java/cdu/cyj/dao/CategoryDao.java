package cdu.cyj.dao;

import cdu.cyj.domain.entity.Category;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-25 10:04:34
 */
public interface CategoryDao {

    /**
     * 通过状态码查询所有记录
     * @param status 状态码
     * @return 对象列表
     */
    List<Category> queryAllByStatus(Integer status);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(Integer id);

    /**
     * 统计总行数
     *
     * @param category 查询条件
     * @return 总行数
     */
    long count(Category category);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int insert(Category category);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Category> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Category> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Category> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Category> entities);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 影响行数
     */
    int update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过Id列表查询分类列表
     *
     * @param ids Id列表
     * @return 分类列表
     */
    List<Category> queryAllByIds(@Param("ids") List<Integer> ids);

    /**
     * 通过文章Id列表查询分类Id列表
     *
     * @param ids 文章Id列表
     * @return 分类Id列表
     */
    List<Integer> queryIdsByArticleIds(@Param("ids") List<Integer> ids);

    /**
     * 通过文章Id查询分类Id
     *
     * @param articleId 文章Id
     * @return 分类Id
     */
    Integer queryIdByArticleId(Integer articleId);

    /**
     * 查询全部分类信息
     *
     * @return 分类列表
     */
    List<Category> queryAll(Category category);

    /**
     * 批量删除分类信息
     *
     * @param ids id列表
     * @return 受影响行数
     */
    int deleteByIdBatch(@Param("ids") List<Integer> ids);
}

