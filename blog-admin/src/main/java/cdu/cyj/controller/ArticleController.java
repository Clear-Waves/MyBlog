package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.ArticleAddDto;
import cdu.cyj.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult<?> addArticle(@RequestBody ArticleAddDto articleDto) {

        return articleService.insert(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult<?> listArticle(Integer pageNum, Integer pageSize) {
        return articleService.articleList(pageNum, pageSize);
    }
}
