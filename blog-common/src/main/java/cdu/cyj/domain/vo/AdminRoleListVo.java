package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleListVo {

    private Integer id;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private Integer status;

    private Date createTime;

    public String getStatus() {
        return status + "";
    }
}
