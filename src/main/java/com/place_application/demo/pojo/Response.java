package com.place_application.demo.pojo;

/**
 * 响应对象
 */
public class Response<T> {
    public static final int OK = 200;
    public static final int ERROR = 500;
    public static final int GET_LOST = 404;
    public static final int COMMON_SENSITIVE = 501;
    private T data;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }



    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
