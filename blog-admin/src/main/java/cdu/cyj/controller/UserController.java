package cdu.cyj.controller;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.dto.UserAddDto;
import cdu.cyj.domain.dto.UserUpdateDto;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/system/user/{id}")
    public ResponseResult<?> userDetail(@PathVariable Integer id) {
        return userService.userDetail(id);
    }

    @PostMapping("/system/user")
    public ResponseResult<?> addUser(@RequestBody UserAddDto userAddDto) {
        return userService.addUser(userAddDto);
    }

    @PutMapping("/system/user")
    public ResponseResult<?> update(@RequestBody UserUpdateDto userUpdateDto) {
        return null;
    }

}
