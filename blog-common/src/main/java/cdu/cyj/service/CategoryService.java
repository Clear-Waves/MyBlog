package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.CategoryAddDto;
import cdu.cyj.domain.dto.CategoryUpdateDto;
import cdu.cyj.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 获取可用的、分类下有文章的分类列表
     *
     * @return 统一返回对象
     */
    ResponseResult<?> getCategoryList();

    /**
     * 获取所有的正常状态的分类列表
     *
     * @return 统一返回对象
     */
    ResponseResult<?> getAllNormalCategoryList();

    /**
     * 分页获取所有分类
     *
     * @param category 分类实体
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return 统一返回对象
     */
    ResponseResult<?> listCategory(Category category, Integer pageNum, Integer pageSize);

    /**
     * 获取分类信息详情
     *
     * @param id 分类id
     * @return 统一返回对象
     */
    ResponseResult<?> getCategoryDetail(Integer id);

    /**
     * 添加分类
     *
     * @param categoryAddDto 分类DTO
     * @return 统一返回对象
     */
    ResponseResult<?> addCategory(CategoryAddDto categoryAddDto);

    /**
     * 更新分类
     *
     * @param categoryUpdateDto 分类DTO
     * @return 统一返回对象
     */
    ResponseResult<?> updateCategory(CategoryUpdateDto categoryUpdateDto);

    /**
     * 批量删除分类
     *
     * @param ids id列表
     * @return 统一返回对象
     */
    ResponseResult<?> deleteCategory(List<Integer> ids);
}
