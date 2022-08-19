package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private Integer id;

    private String nickName;

    private Integer sex;

    private String phoneNumber;

    private String email;
    /**
     * 状态：0：正常；1：停用；-1：删除
     */
    @DecimalMax(value = "1", message = "状态码错误")
    @DecimalMin(value = "0", message = "状态码错误")
    private Integer status;

    @NotBlank(message = "角色列表不能为空")
    private List<Integer> roleIds;

}
