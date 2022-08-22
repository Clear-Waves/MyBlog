package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.CategoryAddDto;
import cdu.cyj.domain.dto.CategoryUpdateDto;
import cdu.cyj.domain.entity.Category;
import cdu.cyj.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/content/category")
@Validated
@Api(tags = "分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    @PreAuthorize("hasAnyAuthority('content:article', 'content:write')")
    @SystemLog(businessName = "列出所有分类")
    public ResponseResult<?> listAllCategory() {
        return categoryService.getAllNormalCategoryList();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('content:category')")
    @SystemLog(businessName = "分页查询分类")
    public ResponseResult<?> listCategory(Category category, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return categoryService.listCategory(category, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('content:category')")
    @SystemLog(businessName = "查询分类详情")
    public ResponseResult<?> getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryDetail(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('content:category')")
    @SystemLog(businessName = "增加分类")
    public ResponseResult<?> addCategory(@RequestBody @Valid CategoryAddDto categoryAddDto) {
        return categoryService.addCategory(categoryAddDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('content:category')")
    @SystemLog(businessName = "更新分类")
    public ResponseResult<?> updateCategory(@RequestBody @Valid CategoryUpdateDto categoryUpdateDto) {
        return categoryService.updateCategory(categoryUpdateDto);
    }

    @PutMapping("/changeStatus")
    @PreAuthorize("hasAuthority('content:category')")
    @SystemLog(businessName = "更改分类状态")
    public ResponseResult<?> changeCategoryStatus(@RequestBody Map<String, Integer> map) {
        return categoryService.changeCategoryStatus(map);
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('content:category')")
    @SystemLog(businessName = "批量删除分类")
    public ResponseResult<?> deleteCategory(@PathVariable @NotEmpty(message = "批量删除不能为空") List<Integer> ids) {
        return categoryService.deleteCategory(ids);
    }

    @GetMapping("/export")
    @PreAuthorize("hasAuthority('content:category')")
    @SystemLog(businessName = "导出分类")
    public void exportCategory(HttpServletResponse response) {
        categoryService.exportCategory(response);
    }

}
