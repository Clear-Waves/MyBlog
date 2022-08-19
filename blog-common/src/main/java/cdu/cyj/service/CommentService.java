package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.CommentAddDto;
import cdu.cyj.domain.entity.Comment;

public interface CommentService {

    /**
     * 分页获取评论list
     *
     * @param articleId 文章id
     * @param pageNum 页
     * @param pageSize 页大小
     * @return 统一返回类型
     */
    ResponseResult<?> commentList(Integer articleId, Integer pageNum, Integer pageSize);

    /**
     * 添加评论
     *
     * @param commentAddDto 评论添加dto
     * @return 统一返回对象
     */
    ResponseResult<?> addComment(CommentAddDto commentAddDto);

    /**
     * 分页获取友链评论list
     *
     * @param pageNum 页
     * @param pageSize 页大小
     * @return 统一返回对象
     */
    ResponseResult<?> linkCommentList(Integer pageNum, Integer pageSize);
}
