package cdu.cyj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2022-07-29 20:38:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = 639455067214966627L;
    
    private Integer id;
    
    private String content;
    
    private Integer articleId;
    
    private Integer userId;
    
    private Integer level;
    
    private Integer fatherId;
    /**
     * 0：文章；1：友联
     */
    private Integer type;
    /**
     * 0：正常；-1：删除
     */
    private Integer status;
    /**
     * 根评论id
     */
    private Integer rootId;
    
    private Integer toCommentUserId;
    
    private Integer toCommentId;
    
    private Integer createBy;
    
    private Date createTime;
    
    private Integer updateBy;
    
    private Date updateTime;

}

