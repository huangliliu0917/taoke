package com.kunion.taoke.model.remote.rest;

/**
 * Created by Administrator on 2017/10/10.
 */

public abstract class BaseBean<T>{
    public static int SUCCESS = 0;
    public static int FAIL = 1;

    private int result;
    private T data;
    private String message;
    private int total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
