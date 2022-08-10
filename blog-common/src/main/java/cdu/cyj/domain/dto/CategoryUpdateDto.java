package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
