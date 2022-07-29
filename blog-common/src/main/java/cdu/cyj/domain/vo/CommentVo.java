package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentVo {

    private Integer id;

    private String content;

    private Integer articleId;
    /**
     * 根评论id
     */
    private Integer rootId;

    private Integer toCommentUserId;

    private Integer toCommentId;

    private Integer createBy;

    private Date createTime;

    private String username;

    private List<CommentVo> children;

}
