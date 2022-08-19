package cdu.cyj.service.impl;

import cdu.cyj.constants.SystemConstants;
import cdu.cyj.dao.ArticleDao;
import cdu.cyj.dao.CommentDao;
import cdu.cyj.dao.UserDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.CommentAddDto;
import cdu.cyj.domain.entity.Comment;
import cdu.cyj.domain.vo.CommentVo;
import cdu.cyj.domain.vo.PageVo;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.service.CommentService;
import cdu.cyj.utils.AutoFilledUtils;
import cdu.cyj.utils.BeanCopyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Override
    public ResponseResult<?> commentList(Integer articleId, Integer pageNum, Integer pageSize) {

        // pageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 查询跟评论
        List<Comment> commentList = commentDao.queryAllByStatusAndTypeAndArticleIdAndRootId(SystemConstants.COMMENT_NORMAL_STATUS, SystemConstants.ARTICLE_COMMENT, articleId, -1);

        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);

        // 查询根评论
        List<CommentVo> commentVoList = toCommentVoList(commentList);

        // 装入子评论
        commentVoList = commentVoList
                .stream()
                .map(commentVo -> commentVo.setChildren(getChildren(commentVo.getId())))
                .collect(Collectors.toList());

        // 封装PageVo
        PageVo pageVo = new PageVo(commentVoList, pageInfo.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> addComment(CommentAddDto commentAddDto) {

        // 校验内容是否为空
        if (StringUtil.isNullOrEmpty(commentAddDto.getContent())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }

        // 检查文章是否允许评论
        if (articleDao.queryById(commentAddDto.getArticleId()).getIsComment() == 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.ARTICLE_NO_COMMENT);
        }

        // 类转换
        Comment comment = BeanCopyUtils.copyBean(commentAddDto, Comment.class);
        // 自动填充
        AutoFilledUtils.autoFillOnInsert(comment);
        // 调用dao插入
        int count = commentDao.insert(comment);

        if (count > 0) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.ADD_ERROR);
        }
    }

    @Override
    public ResponseResult<?> linkCommentList(Integer pageNum, Integer pageSize) {

        // 设置分页
        PageHelper.startPage(pageNum, pageSize);

        // 查询
        List<Comment> commentList = commentDao.queryAllByStatusAndTypeAndRootId(SystemConstants.COMMENT_NORMAL_STATUS, SystemConstants.LINK_COMMENT, -1);

        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);

        List<CommentVo> commentVoList = toCommentVoList(commentList);

        commentVoList = commentVoList
                .stream()
                .map(commentVo -> commentVo.setChildren(getChildren(commentVo.getId())))
                .collect(Collectors.toList());

        // 封装
        PageVo pageVo = new PageVo(commentVoList, pageInfo.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 将Comment List 封装为CommentVo List
     *
     * @param commentList Comment List
     * @return CommentVO List
     */
    private List<CommentVo> toCommentVoList(List<Comment> commentList) {

        // 封装vo
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);

        // 使用stream流进行处理
        commentVos = commentVos
                .stream()
                // 封装用户昵称
                .map(commentVo -> commentVo.setUsername(userDao.queryById(commentVo.getCreateBy()).getNickName()))
                // 封装回复用户昵称 注：判断是否为-1，以免出现空指针
                .map(commentVo -> commentVo.getToCommentUserId() == -1 ? commentVo : commentVo.setToCommentUserName(userDao.queryById(commentVo.getToCommentUserId()).getNickName()))
                // 返回list
                .collect(Collectors.toList());

        return commentVos;
    }

    /**
     * 获取对应根评论的子评论
     *
     * @param rootId 根评论id
     * @return 子评论VO列表
     */
    private List<CommentVo> getChildren(Integer rootId) {
        List<Comment> commentList = commentDao.queryAllByStatusAndRootId(SystemConstants.COMMENT_NORMAL_STATUS, rootId);
        return toCommentVoList(commentList);
    }
}
