package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateDto {

    private Integer id;

    private String title;

    private String content;

    private Integer status;

    private String summary;

    private String thumbnail;

    private Integer isTop;

    private Integer isComment;

    private Integer categoryId;

    private List<Integer> tags;

}
