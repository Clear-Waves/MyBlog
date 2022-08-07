package cdu.cyj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2022-08-07 10:15:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = -20881484060403652L;
    
    private Integer id;
    /**
     * 菜单名
     */
    private String name;
    
    private String path;
    
    private String component;
    
    private Integer visible;
    /**
     * 状态：0：正常，1：停用，-1：删除
     */
    private Integer status;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 图标
     */
    private String icon;
    
    private Integer createBy;
    
    private Date createTime;
    
    private Integer updateBy;
    
    private Date updateTime;
    
    private String title;
    /**
     * 父亲菜单id， 没有则为-1
     */
    private Integer parent;

}

