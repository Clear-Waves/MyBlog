package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagAddDto {

    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 备注
     */
    private String remark;

}
