package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAddDto {

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名不能为空")
    private String name;

    private String description;
    /**
     * 状态：0正常，1禁用，-1删除
     */
    @Max(value = 1, message = "状态参数错误")
    @Min(value = 0, message = "状态参数错误")
    private Integer status;

}
