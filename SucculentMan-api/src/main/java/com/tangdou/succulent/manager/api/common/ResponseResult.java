package com.tangdou.succulent.manager.api.common;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author 木叶丸
 * 返回Json数据的通用返回类
 */
@ToString
public class ResponseResult<T> implements Serializable{

    private int retcode;
    private String msg;
    private T data;

    public ResponseResult() {
            this.retcode = RestResultEnum.SUCCESS.getKey();
        }

    public ResponseResult(int code, String message) {
        this.retcode = code;
        this.msg = message;
    }

    public ResponseResult(RestResultEnum restResultInfoEnum) {
        this.retcode = restResultInfoEnum.getKey();
        this.msg = restResultInfoEnum.getMessage();
    }

    public ResponseResult(RestResultEnum restResultInfoEnum, T data) {
        this.retcode = restResultInfoEnum.getKey();
        this.msg = restResultInfoEnum.getMessage();
        this.data = data;
    }

    public ResponseResult(T data) {
        this.retcode = RestResultEnum.SUCCESS.getKey();
        this.data = data;
    }

    public int getRetcode() {
            return this.retcode;
        }

    public void setRetcode(int retcode) {
            this.retcode = retcode;
        }

    public String getMsg() {
            return this.msg;
        }

    public void setMsg(String msg) {
            this.msg = msg;
        }

    public T getData() {
            return this.data;
        }

    public void setData(T data) {
            this.data = data;
        }

    @Override
    public String toString() {
        return "ResponseResult{retcode=" + this.retcode + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }

}

