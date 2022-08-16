package cdu.cyj.dao;

import cdu.cyj.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-29 20:38:27
 */
@Repository
public interface CommentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param comment 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(Comment comment, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param comment 查询条件
     * @return 总行数
     */
    long count(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Comment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Comment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Comment> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Comment> entities);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过状态码、文章id、根评论id查询
     *
     * @param status 状态码
     * @param articleId 文章id
     * @param rootId 根评论id
     * @return 评论列表
     */
    List<Comment> queryAllByStatusAndTypeAndArticleIdAndRootId(@Param("status") Integer status, @Param("type") Integer type, @Param("articleId")Integer articleId, @Param("rootId")Integer rootId);

    /**
     * 通过状态码、根评论id查询
     *
     * @param status 状态码
     * @param rootId 跟评论id
     * @return 评论列表
     */
    List<Comment> queryAllByStatusAndTypeAndRootId(@Param("status")Integer status, @Param("type") Integer type, @Param("rootId")Integer rootId);

    /**
     * 通过状态码、根评论id查询
     *
     * @param status 状态码
     * @param rootId 跟评论id
     * @return 评论列表
     */
    List<Comment> queryAllByStatusAndRootId(@Param("status")Integer status, @Param("rootId")Integer rootId);

}

