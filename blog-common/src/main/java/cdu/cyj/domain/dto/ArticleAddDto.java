package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAddDto {

    private String title;

    private String content;

    private Integer status;

    private String summary;

    private String thumbnail;

    private Integer isTop;

    private Integer isComment;

    private Integer categoryId;

}
