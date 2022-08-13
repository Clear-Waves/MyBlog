package cdu.cyj.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddDto {

    private String userName;

    private String passWord;

    private String nickName;

    private Integer sex;

    private String phoneNumber;

    private String email;
    /**
     * 状态：0：正常；1：停用；-1：删除
     */
    private Integer status;

    private List<Integer> roleIds;

}
