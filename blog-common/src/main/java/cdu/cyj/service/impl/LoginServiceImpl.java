package cdu.cyj.service.impl;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.LoginUser;
import cdu.cyj.domain.entity.User;
import cdu.cyj.domain.vo.UserInfoVo;
import cdu.cyj.domain.vo.UserLoginVo;
import cdu.cyj.service.LoginService;
import cdu.cyj.utils.BeanCopyUtils;
import cdu.cyj.utils.JwtUtil;
import cdu.cyj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager manager;

    @Autowired
    private RedisCache redisCache;


    @Override
    public ResponseResult<?> login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassWord());

        Authentication authentication = manager.authenticate(authenticationToken);

        if (Objects.isNull(authentication)) {
            throw new RuntimeException("验证失败");
        }

        // 获取用户信息，生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 存入redis
        redisCache.setCacheObject("bloglogin:" + userId, loginUser);
        // 封装返回
        UserInfoVo userInfo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setUserInfo(userInfo);
        userLoginVo.setToken(jwt);

        // 封装统一返回类型

        return ResponseResult.okResult(userLoginVo);
    }

    @Override
    public ResponseResult<?> logout() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer userId = loginUser.getUser().getId();
        redisCache.deleteObject("bloglogin:" + userId);
        return ResponseResult.okResult();
    }
}
