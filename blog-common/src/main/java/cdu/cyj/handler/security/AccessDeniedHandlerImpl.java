package cdu.cyj.handler.security;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();

        ResponseResult<?> result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);

        // 响应
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
