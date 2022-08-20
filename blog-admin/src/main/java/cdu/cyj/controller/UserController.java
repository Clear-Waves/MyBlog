package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.UserAddDto;
import cdu.cyj.domain.dto.UserUpdateDto;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getInfo")
    @SystemLog(businessName = "获取用户信息")
    public ResponseResult<?> userInfo() {
        return userService.adminUserInfo();
    }

    @GetMapping("/system/user/list")
    @PreAuthorize("hasAuthority('system:user')")
    @SystemLog(businessName = "获取用户列表")
    public ResponseResult<?> listUser(User user, Integer pageNum, Integer pageSize) {
        return userService.listUser(user, pageNum, pageSize);
    }

    @GetMapping("/system/user/{id}")
    @PreAuthorize("hasAuthority('system:user')")
    @SystemLog(businessName = "获取用户详情")
    public ResponseResult<?> userDetail(@PathVariable Integer id) {
        return userService.userDetail(id);
    }

    @PostMapping("/system/user")
    @PreAuthorize("hasAuthority('system:user')")
    @SystemLog(businessName = "添加用户")
    public ResponseResult<?> addUser(@RequestBody UserAddDto userAddDto) {
        return userService.addUser(userAddDto);
    }

    @PutMapping("/system/user")
    @PreAuthorize("hasAuthority('system:user')")
    @SystemLog(businessName = "更新用户")
    public ResponseResult<?> update(@RequestBody @Valid UserUpdateDto userUpdateDto) {
        return userService.adminUpdateUser(userUpdateDto);
    }

    @PutMapping("/system/user/changeStatus")
    @PreAuthorize("hasAuthority('system:user')")
    @SystemLog(businessName = "更改用户状态")
    public ResponseResult<?> changeStatus(@RequestBody Map<String, Integer> map) {
        return userService.changeStatus(map.get("userId"), map.get("status"));
    }

    @DeleteMapping("/system/user/{userIds}")
    @PreAuthorize("hasAuthority('system:user')")
    @SystemLog(businessName = "批量删除用户")
    public ResponseResult<?> delUser(@PathVariable List<Integer> userIds) {
        return userService.deleteUser(userIds);
    }

}
