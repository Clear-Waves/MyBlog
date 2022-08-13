package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDetailVo {

    private Integer id;

    private String userName;

    private String nickName;

    private Integer sex;

    private Integer age;

    private String phoneNumber;

    private String email;
    /**
     * 状态：0：正常；1：停用；-1：删除
     */
    private Integer status;


    /*
        配合前端数据类型
     */

    public String getPhonenumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status + "";
    }

    public String getSex(){
        return sex + "";
    }
}
