package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateDto {

    @NotNull(message = "id不能为空")
    private Integer id;
    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;

    private String description;
    /**
     * 状态：0正常，1禁用，-1删除
     */
    @Max(value = 1, message = "状态参数错误")
    @Min(value = 0, message = "状态参数错误")
    private Integer status;

}
