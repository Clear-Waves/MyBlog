package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.ArticleAddDto;
import cdu.cyj.domain.dto.ArticleUpdateDto;
import cdu.cyj.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/article")
@PreAuthorize("hasAuthority('content:article')")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @SystemLog(businessName = "添加文章")
    public ResponseResult<?> addArticle(@RequestBody ArticleAddDto articleDto) {

        return articleService.insert(articleDto);
    }

    @GetMapping("/list")
    @SystemLog(businessName = "查询文章列表")
    public ResponseResult<?> listArticle(String title, String summary, Integer pageNum, Integer pageSize) {
        return articleService.articleList(title, summary, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    @SystemLog(businessName = "查询文章详情")
    public ResponseResult<?> getArticle(@PathVariable Integer id) {
        return articleService.getArticleAdmin(id);
    }

    @PutMapping
    @SystemLog(businessName = "更新文章")
    public ResponseResult<?> updateArticle(@RequestBody ArticleUpdateDto articleDto) {
        return articleService.update(articleDto);
    }

    @DeleteMapping("/{ids}")
    @SystemLog(businessName = "批量删除文章")
    public ResponseResult<?> deleteArticle(@PathVariable List<Integer> ids) {
        return articleService.deleteByIdBatch(ids);
    }
}
