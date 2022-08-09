package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminArticleDetailVo {

    private Integer id;

    private String title;

    private String summary;

    private String content;

    private Integer categoryId;

    private String isComment;

    private String isTop;

    private Integer status;

    private List<Integer> tags;

}
