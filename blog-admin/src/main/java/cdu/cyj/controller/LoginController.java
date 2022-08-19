package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private AdminLoginService loginService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    public ResponseResult<?> login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/logout")
    @SystemLog(businessName = "登出")
    public ResponseResult<?> portalLogout() {
        return loginService.logout();
    }


}
