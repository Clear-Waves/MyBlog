package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkAddDto {

    @NotBlank(message = "友链名称不能为空")
    private String name;

    private String logo;

    private String description;

    @NotBlank(message = "友链地址不能为空")
    private String address;
    /**
     * 0：正常；-1：删除；1：未通过，2：未审核
     */
    @Max(value = 2, message = "状态码错误")
    @Min(value = 0, message = "状态码错误")
    private Integer status;

}
