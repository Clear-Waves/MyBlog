package cdu.cyj.controller;

import cdu.cyj.annotation.SystemLog;
import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.User;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.exception.SystemException;
import cdu.cyj.service.LoginService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Api(tags = "登录")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    @ApiOperation(value = "登录")
    public ResponseResult<?> portalLogin(@RequestBody User user) {

        if (StringUtil.isNullOrEmpty(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_REQUIRED);
        }

        return loginService.login(user);

    }

    @PostMapping("/logout")
    @SystemLog(businessName = "退出登录")
    @ApiOperation("退出登录")
    public ResponseResult<?> portalLogout() {
        return loginService.logout();
    }

}
