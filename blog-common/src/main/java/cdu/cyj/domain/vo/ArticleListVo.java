package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {

    private Integer id;

    private String title;

    private Integer viewCount;

    private Integer likeCount;

    private Date createTime;

    private String summary;

    private String thumbnail;

    private String categoryName;

}
