package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.exception.SystemException;
import cdu.cyj.service.LoginService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "登录")
public class LoginController {

    @Autowired
    @Qualifier("adminLoginServiceImpl")
    private LoginService loginService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    public ResponseResult<?> login(@RequestBody User user) {

        if (StringUtil.isNullOrEmpty(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_REQUIRED);
        }

        return loginService.login(user);
    }

    @PostMapping("/logout")
    @SystemLog(businessName = "登出")
    public ResponseResult<?> portalLogout() {
        return loginService.logout();
    }


}
