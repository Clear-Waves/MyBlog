package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.CategoryAddDto;
import cdu.cyj.domain.dto.CategoryUpdateDto;
import cdu.cyj.domain.entity.Category;
import cdu.cyj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/content/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    @PreAuthorize("hasAnyAuthority('content:article', 'content:write')")
    public ResponseResult<?> listAllCategory() {
        return categoryService.getAllNormalCategoryList();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('content:category')")
    public ResponseResult<?> listCategory(Category category, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return categoryService.listCategory(category, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('content:category')")
    public ResponseResult<?> getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryDetail(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('content:category')")
    public ResponseResult<?> addCategory(@RequestBody @Valid CategoryAddDto categoryAddDto) {
        return categoryService.addCategory(categoryAddDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('content:category')")
    public ResponseResult<?> updateCategory(@RequestBody @Valid CategoryUpdateDto categoryUpdateDto) {
        return categoryService.updateCategory(categoryUpdateDto);
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('content:category')")
    public ResponseResult<?> deleteCategory(@PathVariable List<Integer> ids) {
        return categoryService.deleteCategory(ids);
    }

}
