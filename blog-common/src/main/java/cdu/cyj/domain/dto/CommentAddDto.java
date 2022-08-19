package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddDto {

    @NotBlank(message = "评论内容不能为空")
    private String content;

    private Integer articleId;

    /**
     * 0：文章；1：友联
     */
    @NotNull(message = "评论类型不能为空")
    private Integer type;
    /**
     * 根评论id
     */
    @NotNull(message = "更评论id不能为空")
    private Integer rootId;

    private Integer toCommentUserId;

    private Integer toCommentId;

}
