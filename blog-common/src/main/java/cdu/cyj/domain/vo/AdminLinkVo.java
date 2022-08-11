package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLinkVo {

    private Integer id;

    private String name;

    private String logo;

    private String description;

    private String address;
    /**
     * 0：正常；-1：删除；1：未通过，2：未审核
     */
    private Integer status;

    public String getStatus() {
        return status + "";
    }
}
