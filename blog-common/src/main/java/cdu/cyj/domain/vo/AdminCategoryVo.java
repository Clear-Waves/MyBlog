package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AdminCategoryVo {

    private Integer id;
    /**
     * 分类名称
     */
    private String name;

    private String description;
    /**
     * 状态：0正常，1禁用，-1删除
     */
    private String status;

    public void setStatus(Integer status) {
        this.status = status + "";
    }
}
