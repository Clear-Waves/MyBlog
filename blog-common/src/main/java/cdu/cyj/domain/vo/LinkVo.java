package cdu.cyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {

    private Integer id;

    private String name;

    private String logo;

    private String description;

    private String address;

}
