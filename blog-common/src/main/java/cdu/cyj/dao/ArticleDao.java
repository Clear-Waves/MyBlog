package cdu.cyj.dao;

import cdu.cyj.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-20 13:04:25
 */

@Mapper
public interface ArticleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Article queryById(Integer id);

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<Article> queryAll();


    /**
     * 统计总行数
     *
     * @param article 查询条件
     * @return 总行数
     */
    long count(Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int insert(Article article);

    /**
     * 添加分类数据
     *
     * @return 影响行数
     */
    int insertArticleCategory(@Param("articleId") Integer articleId, @Param("categoryId") Integer categoryId);

    /**
     * 添加标签数据
     *
     * @return 影响行数
     */
    int insertArticleTag(@Param("articleId") Integer articleId, @Param("tagIds") List<Integer> tagIds);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Article> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Article> entities);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int update(Article article);

    /**
     * 批量修改viewCount
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int updateViewCountBatch(@Param("entities") List<Article> entities);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询热门文章，浏览量前十
     *
     * @return 热门文章列表
     */
    List<Article> queryHotArticleList(Integer count);

    /**
     * 通过状态码查询
     *
     * @return 对象列表
     */
    List<Article> queryAllByStatus(Integer status);

    /**
     * 通过分类Id查找文章Id列表
     *
     * @param categoryId 分类Id
     * @return Id列表
     */
    List<Integer> queryIdsByCategoryId(Integer categoryId);

    /**
     * 通过 Id列表 & 状态码 查询对应的文章列表（批量查询）
     *
     * @param ids Id列表
     * @param status 状态码
     * @return 文章列表
     */
    List<Article> queryAllByIdsAndStatus(@Param("ids") List<Integer> ids, @Param("status") Integer status);

    /**
     * 通过title, summary 进行模糊查询
     *
     * @param title 标题
     * @param summary 摘要
     * @return 文章列表
     */
    List<Article> queryAllByTitleAndSummary(@Param("title") String title, @Param("summary") String summary);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Article queryByIdAndStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 更新文章分类中间表
     *
     * @param articleId 文章id
     * @param categoryId 分类id
     * @return 更新个数
     */
    int updateArticleCategory(@Param("articleId") Integer articleId, @Param("categoryId") Integer categoryId);

    /**
     * 更新文章标签中间表
     *
     * @param articleId 文章id
     * @param tagId 标签id
     * @return 跟新个数
     */
    int updateArticleTag(@Param("articleId") Integer articleId, @Param("tagId") Integer tagId);

    /**
     * 批量更新
     *
     * @param articleId
     * @param tagIds
     * @return
     */
    int updateArticleTagBatch(@Param("articleId") Integer articleId, @Param("tagIds") List<Integer> tagIds);
}

