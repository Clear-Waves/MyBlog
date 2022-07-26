package cdu.cyj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2022-07-25 10:04:34
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = 290976708130709206L;
    
    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父分类id
     */
    private Integer pid;
    
    private String description;
    /**
     * 状态：0正常，1禁用，-1删除
     */
    private Integer status;
    
    private Integer createBy;
    
    private Date createTime;
    
    private Integer updateBy;
    
    private Date updateTime;

}

