package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Article;
import cdu.cyj.domain.vo.HotArticleVo;

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
    Article insert(Article article);

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
    ResponseResult<List<HotArticleVo>> getHotArticleList();
}
