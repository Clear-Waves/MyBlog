package cdu.cyj.service.impl;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.ArticleDao;
import cdu.cyj.dao.CategoryDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Article;
import cdu.cyj.domain.entity.Category;
import cdu.cyj.domain.vo.ArticleDetailVo;
import cdu.cyj.domain.vo.ArticleListVo;
import cdu.cyj.domain.vo.HotArticleVo;
import cdu.cyj.domain.vo.PageVo;
import cdu.cyj.service.ArticleService;
import cdu.cyj.utils.BeanCopyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Resource
    private CategoryDao categoryDao;

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
    public ResponseResult<?> getHotArticleList() {
        List<Article> articles = articleDao.queryHotArticleList(SystemConstants.HOT_ARTICLE_COUNT);

        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Integer categoryId) {
        // 定义接受对象
        List<Article> articles = null;



        // 条件查询
        // 判断categoryID是否为空
        if (categoryId != null && categoryId != 0) {
            // 通过categoryId查询
            List<Integer> ids = articleDao.queryIdsByCategoryId(categoryId);
            // 通过状态码查询
            PageHelper.startPage(pageNum, pageSize);
            articles = articleDao.queryAllByIdsAndStatus(ids, SystemConstants.ARTICLE_NORMAL_STATUS);
        } else {
            // 通过状态码查询
            PageHelper.startPage(pageNum, pageSize);
            articles = articleDao.queryAllByStatus(SystemConstants.ARTICLE_NORMAL_STATUS);
        }

        // 分页查询
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        articles = articles
                .stream()
                .filter(article -> categoryDao.queryIdByArticleId(article.getId()) != null)
                .map(article -> article.setCategoryName(categoryDao.queryById(categoryDao.queryIdByArticleId(article.getId())).getName()))
                .collect(Collectors.toList());

        // 封装VO
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo();
        pageVo.setRows(articleListVos);
        pageVo.setTotal(pageInfo.getPages());

        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult<?> getArticle(Integer id) {
        // 查询文章详情以及分类
        Article article = articleDao.queryById(id);
        Category category = categoryDao.queryById(categoryDao.queryIdByArticleId(id));
        article.setCategoryName(category.getName());

        // 封装Vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        articleDetailVo.setCategoryId(category.getId());

        return ResponseResult.okResult(articleDetailVo);
    }
}
