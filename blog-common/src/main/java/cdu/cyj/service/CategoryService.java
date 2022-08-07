package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;

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

}
