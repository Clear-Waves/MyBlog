package cdu.cyj.enums;

public enum AppHttpCodeEnum {

    SUCCESS(200, "操作成功"),

    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "系统错误"),
    PHONENUMBER_EXIST(501, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    LOGIN_ERROR(505, "用户名或密码错误");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
