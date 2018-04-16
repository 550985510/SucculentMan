package com.tangdou.succulent.manager.api.common;

/**
 * @author 木叶丸
 * @date 2018/2/6
 */
public enum  RestResultEnum {

    SUCCESS(2_000_000, "操作成功"),
    ERROR(50_000_000, "操作错误"),

    //登录错误
    ERROR_LOGIN(1_000_001, "用户名或密码错误"),
    ERROR_PASSWORD(1_000_002, "密码错误"),
    ERROR_MOBILE_EXIST(1_000_003, "手机号已存在"),
    ERROR_PASSWORD_LENGTH(1_000_004, "请输入6-32位密码"),
    ERROR_NICKNAME_FORMAT(1_000_005, "请输入2-32位字符作为昵称"),
    ERROR_NICKNAME_EXIST(1_000_006, "昵称已存在"),

    UNKNOWN_ERROR(-1, "未知异常错误"),
    NOT_FOUND_ERROR(-2, "请求地址不存在"),
    FORBIDDEN_ERROR(-3, "没有相关操作权限"),
    ARGUMENT_ERROR(-4, "请求参数错误");

    private int key;
    private String message;

    RestResultEnum(int key, String message) {
        this.key = key;
        this.message = message;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
