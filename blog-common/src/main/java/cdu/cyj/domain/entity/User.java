package cdu.cyj.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-07-28 14:51:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -62934464371346143L;
    
    private Integer id;
    
    private String userName;
    
    private String passWord;
    
    private String nickName;
    
    private Integer sex;
    
    private Integer age;
    
    private String phoneNumber;
    
    private String email;
    /**
     * 状态：0：正常；1：停用；-1：删除
     */
    private Integer status;
    
    private Date createTime;
    
    private Date updateTime;

    private Integer updateBy;

    private Integer createBy;
    
    private String avatar;

}

