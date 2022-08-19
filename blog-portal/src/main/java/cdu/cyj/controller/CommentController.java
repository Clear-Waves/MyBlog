package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.CommentAddDto;
import cdu.cyj.domain.entity.Comment;
import cdu.cyj.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@Validated
@Api(tags = "评论", description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @SystemLog(businessName = "评论列表")
    @ApiOperation(value = "评论列表", notes = "查询一页评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id"),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数")
    })
    public ResponseResult<?> commentList(Integer articleId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }


    @PostMapping
    @SystemLog(businessName = "增加评论")
    @ApiOperation(value = "增加评论", notes = "增加一条评论")
    public ResponseResult<?> addComment(@RequestBody @Valid CommentAddDto comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @SystemLog(businessName = "友链评论列表")
    @ApiOperation(value = "友链评论列表", notes = "查询一页友链评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数")
    })
    public ResponseResult<?> linkCommentList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return commentService.linkCommentList(pageNum, pageSize);
    }

}
