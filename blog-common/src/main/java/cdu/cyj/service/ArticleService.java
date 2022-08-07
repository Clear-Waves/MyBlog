package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.ArticleAddDto;
import cdu.cyj.domain.entity.Article;

import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2022-07-20 13:04:29
 */
public interface ArticleService {

    /**
     * 查询所有数据列表
     * @return 数据列表
     */
    List<Article> queryAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Article queryById(Integer id);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    ResponseResult<?> insert(ArticleAddDto article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    Article update(Article article);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询人们文章
     *
     * @return 统一结果对象
     */
    ResponseResult<?> getHotArticleList();

    /**
     * 查询文章列表
     *
     * @param pageNum 页码
     * @param pageSize 一页的内容数
     * @param categoryId 分类id
     * @return 统一返回结果
     */
    ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Integer categoryId);

    /**
     * 查询文章详情
     *
     * @param id 文章id
     * @return 统一返回结果
     */
    ResponseResult<?> getArticle(Integer id);

    /**
     * 文章访问量递增
     *
     * @param id 文章id
     * @return 统一返回结果
     */
    ResponseResult<?> viewCountIncrement(Integer id);

    /**
     * 获取所有文章列表
     *
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return 统一返回结果
     */
    ResponseResult<?> articleList(Integer pageNum, Integer pageSize);
}
