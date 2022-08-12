package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getInfo")
    public ResponseResult<?> userInfo() {
        return userService.adminUserInfo();
    }

    @GetMapping("/system/user/list")
    public ResponseResult<?> listUser(User user, Integer pageNum, Integer pageSize) {
        return userService.listUser(user, pageNum, pageSize);
    }

}
