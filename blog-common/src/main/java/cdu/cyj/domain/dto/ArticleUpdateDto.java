package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "文章标题不能为空")
    private String title;

    @NotBlank(message = "文章内容更不能为空")
    private String content;

    @Max(value = 1, message = "状态传入错误")
    @Min(value = 0, message = "状态传入错误")
    private Integer status;

    private String summary;

    private String thumbnail;

    @Max(value = 1, message = "置顶传入错误")
    @Min(value = 0, message = "置顶传入错误")
    private Integer isTop;

    @Max(value = 1, message = "评论传入错误")
    @Min(value = 0, message = "评论传入错误")
    private Integer isComment;

    @NotNull(message = "分类id不能为空")
    private Integer categoryId;

    private List<Integer> tags;
}
