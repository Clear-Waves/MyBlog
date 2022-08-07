package cdu.cyj.service.impl;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.LoginUser;
import cdu.cyj.domain.entity.User;
import cdu.cyj.service.AdminLoginService;
import cdu.cyj.utils.JwtUtil;
import cdu.cyj.utils.RedisCache;
import cdu.cyj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

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
        redisCache.setCacheObject("adminlogin:" + userId, loginUser, 2, TimeUnit.HOURS);

        // 封装统一返回类型
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult<?> logout() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer userId = loginUser.getUser().getId();
        redisCache.deleteObject("adminlogin:" + userId);
        return ResponseResult.okResult();
    }

}
