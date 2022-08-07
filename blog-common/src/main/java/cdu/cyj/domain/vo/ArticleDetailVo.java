package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

    private Integer id;

    private String title;

    private String content;

    private Integer viewCount;

    private Date createTime;

    private String categoryName;

    private Integer categoryId;

    private Integer isComment;

}
