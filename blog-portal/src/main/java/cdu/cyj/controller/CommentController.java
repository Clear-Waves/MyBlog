package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Comment;
import cdu.cyj.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论", description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @ApiOperation(value = "评论列表", notes = "查询一页评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id"),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数")
    })
    public ResponseResult<?> commentList(Integer articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }


    @PostMapping
    @ApiOperation(value = "增加评论", notes = "增加一条评论")
    public ResponseResult<?> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表", notes = "查询一页友链评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数")
    })
    public ResponseResult<?> linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.linkCommentList(pageNum, pageSize);
    }

}
