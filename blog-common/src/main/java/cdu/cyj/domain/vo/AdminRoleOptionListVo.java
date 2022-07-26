package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleOptionListVo {

    private Integer id;

    private String roleName;

    private Integer roleSort;

    private Integer status;

    private String remark;

}
