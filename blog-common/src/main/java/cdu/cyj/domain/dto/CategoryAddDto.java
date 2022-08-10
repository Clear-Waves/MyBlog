package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAddDto {

    /**
     * 分类名称
     */
    @NotNull(message = "分类名不能为空")
    private String name;
    /**
     * 父分类id
     */
    private Integer pid;

    private String description;
    /**
     * 状态：0正常，1禁用，-1删除
     */
    private Integer status;

}
