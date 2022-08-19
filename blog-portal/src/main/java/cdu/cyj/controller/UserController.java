package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.UserInfoUpdateDto;
import cdu.cyj.domain.dto.UserRegisterDto;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
@Api(tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @SystemLog(businessName = "获取用户信息")
    @ApiOperation("获取用户信息")
    public ResponseResult<?> userInfo() {
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @ApiOperation("更新用户信息")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult<?> updateUserInfo(@RequestBody @Valid UserInfoUpdateDto user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @SystemLog(businessName = "用户注册")
    @ApiOperation("用户注册")
    public ResponseResult<?> register(@RequestBody @Valid UserRegisterDto user) {
        return userService.register(user);
    }

}
