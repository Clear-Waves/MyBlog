package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkUpdateDto {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String logo;

    private String description;

    @NotBlank(message = "链接地址不能为空")
    private String address;
    /**
     * 0：正常；-1：删除；1：未通过，2：未审核
     */
    @DecimalMax(value = "2", message = "状态码错误")
    @DecimalMin(value = "0", message = "状态码错误")
    private Integer status;

}
