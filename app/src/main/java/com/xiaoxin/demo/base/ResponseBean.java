package com.xiaoxin.demo.base;

/**
 * Created by chris on 2017/10/16.
 */

public class ResponseBean<T> extends BaseEntity {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
