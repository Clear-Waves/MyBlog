package cdu.cyj.contorller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Comment;
import cdu.cyj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult<?> commentList(Integer articleId, Integer pageNum, Integer pageSize) {

        return commentService.commentList(articleId, pageNum, pageSize);

    }


    @PostMapping
    public ResponseResult<?> addComment(@RequestBody Comment comment) {

        return commentService.addComment(comment);

    }

    @GetMapping("/linkCommentList")
    public ResponseResult<?> linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.linkCommentList(pageNum, pageSize);
    }

}
