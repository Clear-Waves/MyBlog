package cdu.cyj.service;

import cdu.cyj.domain.ResponseResult;

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

}
