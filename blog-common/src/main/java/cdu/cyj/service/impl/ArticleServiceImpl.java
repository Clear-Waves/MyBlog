package cdu.cyj.service.impl;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.ArticleDao;
import cdu.cyj.dao.CategoryDao;
import cdu.cyj.dao.TagDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.ArticleAddDto;
import cdu.cyj.domain.dto.ArticleUpdateDto;
import cdu.cyj.domain.entity.Article;
import cdu.cyj.domain.entity.Category;
import cdu.cyj.domain.entity.Tag;
import cdu.cyj.domain.vo.*;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.service.ArticleService;
import cdu.cyj.utils.AutoFilledUtils;
import cdu.cyj.utils.BeanCopyUtils;
import cdu.cyj.utils.RedisCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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

    @Autowired
    private TagDao tagDao;

    @Autowired
    private RedisCache redisCache;

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
     * @param articleDto 实例对象
     * @return 实例对象
     */
    @Override
    public ResponseResult<?> insert(ArticleAddDto articleDto) {
        // 获取dto参数
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        AutoFilledUtils.autoFillOnInsert(article);
        // 校验参数是否合法
        if (!checkParameters(articleDto.getCategoryId(), articleDto.getTags())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_ERROR);
        }
        // 插入 article, category—article, tag—article 三张表
        int countArticle = this.articleDao.insert(article);
        int countCategory = this.articleDao.insertArticleCategory(article.getId(), articleDto.getCategoryId());
        int countTag = this.articleDao.insertArticleTag(article.getId(), articleDto.getTags());
        if (countArticle == 1 && countCategory == 1 && countTag == articleDto.getTags().size()) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 修改数据
     *
     *
     * @param articleDto 实例对象dto
     * @return 实例对象
     */
    @Override
    public ResponseResult<?> update(ArticleUpdateDto articleDto) {

        // 获取dto参数
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        AutoFilledUtils.autoFillOnUpdate(article);
        if (!checkParameters(articleDto.getCategoryId(), articleDto.getTags())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_ERROR);
        }

        // 调用dao更新article表
        int countArticle = this.articleDao.update(article);
        // 调用dao更新article-tag中间表 由于多对多，使用先删除在添加
        // int countTags = this.articleDao.updateArticleTagBatch(article.getId(), articleDto.getTags());
        this.articleDao.deleteArticleTagByArticleId(article.getId());
        int countTags = 0;
        if (articleDto.getTags() != null && articleDto.getTags().size() > 0) {
            countTags = this.articleDao.insertArticleTag(article.getId(), articleDto.getTags());
        }
        // 调用dao更新article-category中间表
        int countCategory = this.articleDao.updateArticleCategory(article.getId(), articleDto.getCategoryId());
        // 返回
        if (countArticle == 1 && countCategory == 1 && (articleDto.getTags() == null || countTags == articleDto.getTags().size())) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public ResponseResult<?> deleteById(Integer id) {
        // 逻辑删除、
        int countArticle = this.articleDao.deleteById(id);
        // 删除相关中间表
        int countArticleTag = this.articleDao.deleteArticleTagByArticleId(id);
        int countArticleCategory = this.articleDao.deleteArticleCategoryByArticleId(id);

        if (countArticle == 1 & countArticleCategory == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> deleteByIdBatch(List<Integer> ids) {
        // 逻辑删除、
        int countArticle = this.articleDao.deleteByIdBatch(ids);
        // 删除相关中间表
        int countArticleTag = this.articleDao.deleteArticleTagByArticleIdBatch(ids);
        int countArticleCategory = this.articleDao.deleteArticleCategoryByArticleIdBatch(ids);

        if (countArticle == ids.size() && countArticleCategory > 0) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
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
        pageVo.setTotal(pageInfo.getTotal());

        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult<?> getArticle(Integer id) {
        // 查询文章详情以及分类
        Article article = articleDao.queryByIdAndStatus(id, SystemConstants.ARTICLE_NORMAL_STATUS);
        Category category = categoryDao.queryById(categoryDao.queryIdByArticleId(id));
        article.setCategoryName(category.getName());

        // 浏览次数使用redis中的最新数据
        article.setViewCount(redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT_KEY, id.toString()));

        // 封装Vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        articleDetailVo.setCategoryId(category.getId());

        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult<?> viewCountIncrement(Integer id) {

        // 更新redis中的viewCount
        Integer current = redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT_KEY, id.toString());
        redisCache.setCacheMapValue(SystemConstants.ARTICLE_VIEWCOUNT_KEY, id.toString(), current + 1);
        // 返回结果
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<?> articleList(String title, String summary, Integer pageNum, Integer pageSize) {

        // 分页
        PageHelper.startPage(pageNum, pageSize);
        // dao查询
        List<Article> articleList = articleDao.queryAllByTitleAndSummary(title, summary);
        // 封装返回
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        List<ArticleListVo> articleListVoList = BeanCopyUtils.copyBeanList(articleList, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVoList, pageInfo.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> getArticleAdmin(Integer id) {
        // 查询文章详情以及分类以及标签
        Article article = articleDao.queryById(id);
        Integer categoryId = categoryDao.queryIdByArticleId(id);
        List<Integer> ids = tagDao.queryIdsByArticleId(article.getId());

        // 封装Vo
        AdminArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, AdminArticleDetailVo.class);
        if (ids != null && ids.size() > 0) {
            // List<Tag> tags = tagDao.queryAllByIds(ids);
            // List<TagVo> tagVoList = BeanCopyUtils.copyBeanList(tags, TagVo.class);
            articleDetailVo.setTags(ids);
        }
        articleDetailVo.setCategoryId(categoryId);

        // 对接前端char类型
        articleDetailVo.setIsTop("" + article.getIsTop());
        articleDetailVo.setIsComment("" + article.getIsComment());

        return ResponseResult.okResult(articleDetailVo);
    }











    /**
     * 校验dto的参数是否合法
     *
     * @param categoryId 分类id
     * @param tagIds 标签id集合
     * @return 是否合法
     */
    private boolean checkParameters(Integer categoryId, List<Integer> tagIds) {
        // 校验参数是否合法
        // 校验category状态是否正常
        Category category = categoryDao.queryById(categoryId);
        if (category == null || category.getStatus() != 0) {
            return false;
        }
        // 校验tag状态是否正常
        if (tagIds != null && tagIds.size() != 0) {
            List<Tag> tags = tagDao.queryAllByIds(tagIds);
            if (tags.stream().filter(tag -> tag.getStatus() == 0).count() != tagIds.size()) {
                return false;
            }
        }
        return true;
    }
}
