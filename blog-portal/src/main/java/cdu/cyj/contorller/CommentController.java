package cdu.cyj.contorller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult<?> commentList(Integer articleId, Integer pageNum, Integer pageSize) {

        return commentService.commentList(articleId, pageNum, pageSize);

    }

}
