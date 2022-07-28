package cdu.cyj.domain;

import cdu.cyj.enums.AppHttpCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseResult<?> errorResult(AppHttpCodeEnum appHttpCodeEnum) {
        ResponseResult<?> result = new ResponseResult<>();
        return result.error(appHttpCodeEnum.getCode(), appHttpCodeEnum.getMsg());
    }

    public static ResponseResult<?> okResult() {
        return new ResponseResult<>();
    }

    public static ResponseResult<?> okResult(Integer code, String msg, Object data) {
        ResponseResult<Object> result = new ResponseResult<>();
        return result.ok(code, data, msg);
    }

    public static ResponseResult<?> okResult(Integer code, String msg) {
        ResponseResult<?> result = new ResponseResult<>();
        return result.ok(code, null, msg);
    }

    public static ResponseResult<?> okResult(Object data) {
        ResponseResult<Object> result = new ResponseResult<>();
        result.setData(data);
        return result;
    }

    private ResponseResult<?> error(Integer code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    private ResponseResult<?> ok(Integer code, Object data, String msg) {
        return new ResponseResult<>(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
