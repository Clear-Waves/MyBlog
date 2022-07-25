package cdu.cyj.service.impl;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.ArticleDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Article;
import cdu.cyj.domain.vo.HotArticleVo;
import cdu.cyj.service.ArticleService;
import cdu.cyj.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2022-07-20 13:04:29
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    /**
     * 查询所有数据列表
     * @return 数据列表
     */
    @Override
    public List<Article> queryAll() {
        return articleDao.queryAll();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Article queryById(Integer id) {
        return this.articleDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article insert(Article article) {
        this.articleDao.insert(article);
        return article;
    }

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article update(Article article) {
        this.articleDao.update(article);
        return this.queryById(article.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.articleDao.deleteById(id) > 0;
    }

    @Override
    public ResponseResult getHotArticleList() {
        List<Article> articles = articleDao.queryHotArticleList(SystemConstants.HOT_ARTICLE_COUNT);

        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }
}
