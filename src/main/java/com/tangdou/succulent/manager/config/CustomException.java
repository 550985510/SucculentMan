package com.tangdou.succulent.manager.config;

/**
 * @author 木叶丸
 * @date 2018/2/6
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -4488377121094064672L;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
