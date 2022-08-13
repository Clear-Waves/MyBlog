package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserListVo {

    private Integer id;

    private String userName;

    private String nickName;


    private String phoneNumber;
    /**
     * 状态：0：正常；1：停用；-1：删除
     */
    private Integer status;

    private Date createTime;

    public String getStatus() {
        return status + "";
    }

    public String getPhonenumber() {
        return phoneNumber;
    }
}
