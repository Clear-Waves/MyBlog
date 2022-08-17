package cdu.cyj.handler.exception;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult<?> systemExceptionHandler(SystemException e) {
        log.error("出现异常：{}", e.getMsg(), e);
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<?> ExceptionHandler(Exception e) {
        log.error("出现异常:{}", e.getMessage(), e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<?> parameterExceptionHandler(MethodArgumentNotValidException e) {
        log.error("出现异常：{}", e.getMessage(), e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> messages = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ResponseResult<List<String>> responseResult = new ResponseResult<>(AppHttpCodeEnum.PARAMETER_ERROR.getCode(), AppHttpCodeEnum.PARAMETER_ERROR.getMsg());
        responseResult.setData(messages);
        return responseResult;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseResult<?> userNamePasswordExceptionHandler(BadCredentialsException e) {
        log.error("出现异常：{}", e.getMessage(), e);
        return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult<?> accessDeniedException(AccessDeniedException e) {
        log.error("出现异常：{}", e.getMessage(), e);
        return ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
    }

}
