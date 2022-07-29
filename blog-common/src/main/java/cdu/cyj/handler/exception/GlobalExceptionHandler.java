package cdu.cyj.handler.exception;

import cdu.cyj.domain.ResponseResult;
import cdu.cyj.enums.AppHttpCodeEnum;
import cdu.cyj.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
