package cdu.cyj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author makejava
 * @since 2022-07-20 13:04:28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Article implements Serializable {
    private static final long serialVersionUID = -84742131558056225L;
    
    private Integer id;
    
    private String title;
    
    private String content;
    
    private Integer viewCount;
    
    private Integer likeCount;
    
    private Integer commentCount;
    
    private Integer status;
    
    private Integer userId;
    
    private Date createTime;
    
    private Date updateTime;
    
    private String summary;

    private String thumbnail;

    private String categoryName;
}

