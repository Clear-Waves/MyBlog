package cdu.cyj.service.impl;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.ArticleDao;
import cdu.cyj.dao.CategoryDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.CategoryAddDto;
import cdu.cyj.domain.dto.CategoryUpdateDto;
import cdu.cyj.domain.entity.Article;
import cdu.cyj.domain.entity.Category;
import cdu.cyj.domain.vo.AdminCategoryVo;
import cdu.cyj.domain.vo.CategoryVo;
import cdu.cyj.domain.vo.PageVo;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.service.CategoryService;
import cdu.cyj.utils.AutoFilledUtils;
import cdu.cyj.utils.BeanCopyUtils;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
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

    @Override
    public ResponseResult<?> listCategory(Category category, Integer pageNum, Integer pageSize) {

        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Category> categories = categoryDao.queryAll(category);
        PageInfo<Category> pageInfo = new PageInfo<>(categories);

        // 封装vo
        List<AdminCategoryVo> adminCategoryVos = BeanCopyUtils.copyBeanList(categories, AdminCategoryVo.class);
        PageVo pageVo = new PageVo(adminCategoryVos, pageInfo.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> getCategoryDetail(Integer id) {
        // 调用dao查询相关分类信息
        Category category = categoryDao.queryById(id);
        // 封装vo
        AdminCategoryVo adminCategoryVo = BeanCopyUtils.copyBean(category, AdminCategoryVo.class);
//        // 处理字符与整型问题
//        adminCategoryVo.setStatus("" + category.getStatus());
        // 返回
        return ResponseResult.okResult(adminCategoryVo);
    }

    @Override
    public ResponseResult<?> addCategory(CategoryAddDto categoryAddDto) {

        // pojo转换
        Category category = BeanCopyUtils.copyBean(categoryAddDto, Category.class);
        // 自动填充时间
        AutoFilledUtils.autoFillOnInsert(category);
        // 调用dao新增
        int insert = categoryDao.insert(category);
        // 封装返回
        if (insert == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> updateCategory(CategoryUpdateDto categoryUpdateDto) {
        // 转换POJO
        Category category = BeanCopyUtils.copyBean(categoryUpdateDto, Category.class);
        // 自动填充时间
        AutoFilledUtils.autoFillOnUpdate(category);
        // 调用dao更新
        int update = categoryDao.update(category);
        // 封装返回
        if (update == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> deleteCategory(List<Integer> ids) {
        // 调用dao进行逻辑删除
        int delete = categoryDao.deleteByIdBatch(ids);
        // 返回
        if (delete == ids.size()) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> changeCategoryStatus(Map<String, Integer> map) {
        // 校验参数
        if (map.get("categoryId") == null || map.get("status") == null || map.get("status") > 1 || map.get("status") < 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAMETER_ERROR);
        }
        // 调用dao跟新
        Category category = new Category();
        category.setId(map.get("categoryId"));
        category.setStatus(map.get("status"));
        // 自动填充
        AutoFilledUtils.autoFillOnUpdate(category);
        int update = categoryDao.update(category);
        // 封装返回
        if (update == 1) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void exportCategory(HttpServletResponse response) {

        List<Category> categories = categoryDao.queryAll(new Category());
        // 封装vo
        List<AdminCategoryVo> adminCategoryVos = BeanCopyUtils.copyBeanList(categories, AdminCategoryVo.class);

        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("category", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), AdminCategoryVo.class).sheet().doWrite(adminCategoryVos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
