package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTagVo {

    private Integer id;

    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：-1：删除；0：正常
     */
    private Integer status;

    public String getStatus() {
        return status + "";
    }
}
