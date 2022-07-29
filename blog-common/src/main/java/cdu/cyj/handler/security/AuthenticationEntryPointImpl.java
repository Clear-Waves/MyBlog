package cdu.cyj.handler.security;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();

        ResponseResult<?> result;
        // 判断异常类型
        if (authException instanceof BadCredentialsException) {
            // BadCredentialsException
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), authException.getMessage());
        } else if (authException instanceof InsufficientAuthenticationException) {
            // InsufficientAuthenticationException
            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN.getCode(), authException.getMessage());
        } else {
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
        // 返回统一响应格式
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
