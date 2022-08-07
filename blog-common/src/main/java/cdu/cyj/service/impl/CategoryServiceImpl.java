package cdu.cyj.service.impl;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.ArticleDao;
import cdu.cyj.dao.CategoryDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Article;
import cdu.cyj.domain.entity.Category;
import cdu.cyj.domain.vo.CategoryVo;
import cdu.cyj.service.CategoryService;
import cdu.cyj.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private ArticleDao articleDao;


    @Override
    public ResponseResult<?> getCategoryList() {

        List<Article> articleList = articleDao.queryAllByStatus(SystemConstants.ARTICLE_NORMAL_STATUS);

        List<Integer> articleIds = articleList.stream()
                .map(Article::getId)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> categoryIds = categoryDao.queryIdsByArticleIds(articleIds).stream()
                .distinct()
                .collect(Collectors.toList());

        List<Category> categoryList = categoryDao.queryAllByIds(categoryIds).stream()
                .filter(category -> category.getStatus().equals(SystemConstants.CATEGORY_NORMAL_STATUS))
                .collect(Collectors.toList());

        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public ResponseResult<?> getAllNormalCategoryList() {

        List<Category> categoryList = categoryDao.queryAllByStatus(SystemConstants.CATEGORY_NORMAL_STATUS);
        List<CategoryVo> categoryVoList = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);
        return ResponseResult.okResult(categoryVoList);
    }
}
