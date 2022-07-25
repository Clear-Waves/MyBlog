package cdu.cyj.domain.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2022-07-25 10:04:34
 */
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

