package cdu.cyj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Tag)实体类
 *
 * @author makejava
 * @since 2022-08-05 15:10:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {
    private static final long serialVersionUID = 620685625944347449L;
    
    private Integer id;
    
    private String name;
    
    private Integer createBy;
    
    private Date createTime;
    
    private Integer updateBy;
    
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：-1：删除；0：正常
     */
    private Integer status;

}

