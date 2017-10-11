package com.kunion.taoke.model.remote.rest;

/**
 * Created by Administrator on 2017/10/10.
 */

public abstract class BaseBean<T>{
    private int result;
    private T data;
    private String message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
