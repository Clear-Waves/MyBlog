package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private Integer id;

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
