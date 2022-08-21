package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagUpdateDto {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 备注
     */
    private String remark;

}
