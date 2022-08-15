package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAddDto {

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private Integer status;

    private String remark;

    private List<Integer> menuIds;

}
