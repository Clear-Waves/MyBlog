package cdu.cyj.filter;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.domain.entity.LoginUser;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.utils.JwtUtil;
import cdu.cyj.utils.RedisCache;
import cdu.cyj.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");

        // token为空可能是不需要登录，所以直接放行
        if (StringUtil.isNullOrEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = null;

        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            // 处理异常：包括登录失效、token不合法等等
            e.printStackTrace();

            ResponseResult<?> result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);

            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        // 解析token
        String userId = claims.getSubject();
        // 查询redis
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:" + userId);
        //LoginUser loginUser = cacheObject.toJavaObject(LoginUser.class);

        if (Objects.isNull(loginUser)) {
            // 登录过期
            ResponseResult<?> result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        // 将查询出的登录用户信息存入security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

    }
}
