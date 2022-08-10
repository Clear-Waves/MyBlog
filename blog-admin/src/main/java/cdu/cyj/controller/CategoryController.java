package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.CategoryAddDto;
import cdu.cyj.domain.dto.CategoryUpdateDto;
import cdu.cyj.domain.entity.Category;
import cdu.cyj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseResult<?> listAllCategory() {
        return categoryService.getAllNormalCategoryList();
    }

    @GetMapping("/list")
    public ResponseResult<?> listCategory(Category category, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return categoryService.listCategory(category, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseResult<?> getCategory(@PathVariable Integer id) {
        return categoryService.getCategoryDetail(id);
    }

    @PostMapping
    public ResponseResult<?> addCategory(@RequestBody @Valid CategoryAddDto categoryAddDto) {
        return categoryService.addCategory(categoryAddDto);
    }

    @PutMapping
    public ResponseResult<?> updateCategory(@RequestBody @Valid CategoryUpdateDto categoryUpdateDto) {
        return categoryService.updateCategory(categoryUpdateDto);
    }

    @DeleteMapping("/{ids}")
    public ResponseResult<?> deleteCategory(@PathVariable List<Integer> ids) {
        return categoryService.deleteCategory(ids);
    }

}
