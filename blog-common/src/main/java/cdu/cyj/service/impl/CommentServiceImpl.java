package cdu.cyj.service.impl;

import cdu.cyj.dao.CommentDao;
import cdu.cyj.dao.UserDao;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.Comment;
import cdu.cyj.domain.vo.CommentVo;
import cdu.cyj.domain.vo.PageVo;
import cdu.cyj.service.CommentService;
import cdu.cyj.utils.BeanCopyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public ResponseResult<?> commentList(Integer articleId, Integer pageNum, Integer pageSize) {

        // pageHelper
        PageHelper.startPage(pageNum, pageSize);
        // 查询
        List<Comment> commentList = commentDao.queryAllByStatusAndArticleIdAndRootId(0, articleId, -1);

        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);

        // 封装vo
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);

        // 使用stream流进行处理
        commentVos = commentVos
                .stream()
                .map(commentVo -> commentVo.setUsername(userDao.queryById(commentVo.getCreateBy()).getUserName()))
                .collect(Collectors.toList());

        // 封装PageVo
        PageVo pageVo = new PageVo(commentVos, pageInfo.getPages());

        return ResponseResult.okResult(pageVo);
    }
}
