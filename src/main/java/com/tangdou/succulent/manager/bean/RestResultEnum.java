package com.tangdou.succulent.manager.bean;

/**
 * @author 木叶丸
 * @date 2018/2/6
 */
public enum  RestResultEnum {

    SUCCESS(2000000, "操作成功"),
    ERROR(50000000, "操作错误"),
    LOGIN_ERROR(1000001, "用户名或密码错误"),
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
