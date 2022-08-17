package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.UserAddDto;
import cdu.cyj.domain.dto.UserUpdateDto;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getInfo")
    public ResponseResult<?> userInfo() {
        return userService.adminUserInfo();
    }

    @GetMapping("/system/user/list")
    @PreAuthorize("hasAuthority('system:user')")
    public ResponseResult<?> listUser(User user, Integer pageNum, Integer pageSize) {
        return userService.listUser(user, pageNum, pageSize);
    }

    @GetMapping("/system/user/{id}")
    @PreAuthorize("hasAuthority('system:user')")
    public ResponseResult<?> userDetail(@PathVariable Integer id) {
        return userService.userDetail(id);
    }

    @PostMapping("/system/user")
    @PreAuthorize("hasAuthority('system:user')")
    public ResponseResult<?> addUser(@RequestBody UserAddDto userAddDto) {
        return userService.addUser(userAddDto);
    }

    @PutMapping("/system/user")
    @PreAuthorize("hasAuthority('system:user')")
    public ResponseResult<?> update(@RequestBody @Valid UserUpdateDto userUpdateDto) {
        return userService.adminUpdateUser(userUpdateDto);
    }

    @PutMapping("/system/user/changeStatus")
    @PreAuthorize("hasAuthority('system:user')")
    public ResponseResult<?> changeStatus(@RequestBody Map<String, Integer> map) {
        return userService.changeStatus(map.get("userId"), map.get("status"));
    }

    @DeleteMapping("/system/user/{userIds}")
    @PreAuthorize("hasAuthority('system:user')")
    public ResponseResult<?> delUser(@PathVariable List<Integer> userIds) {
        return userService.deleteUser(userIds);
    }

}
