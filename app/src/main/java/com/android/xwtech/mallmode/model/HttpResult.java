package com.android.xwtech.mallmode.model;

/**
 * @author XW-Laq
 * @date 2017/12/18
 */

public class HttpResult<T> {

    private int status_code;
    private String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
