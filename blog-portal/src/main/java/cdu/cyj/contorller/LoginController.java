package cdu.cyj.contorller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.exception.SystemException;
import cdu.cyj.service.LoginService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult<?> portalLogin(@RequestBody User user) {

        if (StringUtil.isNullOrEmpty(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_REQUIRED);
        }

        return loginService.login(user);

    }

    @PostMapping("/logout")
    public ResponseResult<?> portalLogout() {
        return loginService.logout();
    }

}
