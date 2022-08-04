package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @ApiOperation("获取用户信息")
    public ResponseResult<?> userInfo() {
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @ApiOperation("更新用户信息")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult<?> updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResponseResult<?> register(@RequestBody User user) {
        return userService.register(user);
    }

}
