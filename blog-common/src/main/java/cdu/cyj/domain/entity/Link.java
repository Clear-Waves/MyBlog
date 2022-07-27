package cdu.cyj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Link)实体类
 *
 * @author makejava
 * @since 2022-07-27 09:54:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link implements Serializable {
    private static final long serialVersionUID = -65558140863542709L;
    
    private Integer id;
    
    private String name;
    
    private String logo;
    
    private String description;
    
    private String address;
    /**
     * 0：正常；-1：删除；1：未通过，2：未审核
     */
    private Integer status;
    
    private Integer createBy;
    
    private Date createTime;
    
    private Integer updateBy;
    
    private Date updateTime;

}

