package com.xiaoxin.demo.base;

/**
 * Created by chris on 2017/10/16.
 */

public class ErrorResponse<T> extends BaseEntity {
    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
