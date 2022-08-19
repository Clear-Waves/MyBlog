package cdu.cyj.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String passWord;

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @NotBlank(message = "电子邮件不能为空")
    private String email;

}
