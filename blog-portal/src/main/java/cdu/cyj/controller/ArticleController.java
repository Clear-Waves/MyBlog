package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.ArticleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Api(tags = "文章", description = "文章操作相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    @ApiOperation(value = "热门文章列表", notes = "获取浏览量前十的文章列表")
    public ResponseResult<?> hotArticleList() {
        return articleService.getHotArticleList();
    }

    @GetMapping("/articleList")
    @ApiOperation(value = "文章列表", notes = "获取一页文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小"),
            @ApiImplicitParam(name = "categoryId", value = "分类ID")
    })
    public ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Integer categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "文章详情", notes = "获取一篇文章的详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章id")
    })
    public ResponseResult<?> article(@PathVariable Integer id) {
        return articleService.getArticle(id);
    }

    @PutMapping("/updateViewCount/{id}")
    @ApiOperation(value = "更新文章访问量", notes = "更新文章访问量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章id")
    })
    public ResponseResult<?> updateViewCount(@PathVariable Integer id) {
        return articleService.viewCountIncrement(id);
    }

}
