package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateDto {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @NotBlank(message = "角色key不能为空")
    private String roleKey;

    private Integer roleSort;

    @Max(value = 1, message = "状态参数错误")
    @Min(value = 0, message = "状态参数错误")
    private Integer status;

    private String remark;

    private List<Integer> menuIds;

}
