package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private AdminUserService loginService;

    @PostMapping("/login")
    public ResponseResult<?> login(@RequestBody User user) {
        return loginService.login(user);
    }

}
