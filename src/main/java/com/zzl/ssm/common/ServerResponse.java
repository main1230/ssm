package com.zzl.ssm.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * Created by zhangzl
 * 描述：服务器返回数据统一处理
 * 日期：  2017/5/19.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ServerResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }


    public static <T> ServerResponse<T> success() {
        return new ServerResponse<T>(ServerResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> successMsg(String msg) {
        return new ServerResponse<T>(ServerResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> successData(T data) {
        return new ServerResponse<T>(ServerResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> success(String msg, T data) {
        return new ServerResponse<T>(ServerResponseCode.SUCCESS.getCode(), msg, data);
    }


    public static <T> ServerResponse<T> error() {
        return new ServerResponse<T>(ServerResponseCode.ERROR.getCode(), ServerResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> errorMsg(String errorMessage) {
        return new ServerResponse<T>(ServerResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ServerResponse<T> error(int errorCode, String errorMessage) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }
}
