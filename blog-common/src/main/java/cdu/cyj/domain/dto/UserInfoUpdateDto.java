package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateDto {

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    private Integer sex;

    private String phoneNumber;

    private String email;

    private String avatar;

}
