package com.zzl.ssm.dto;

/**
 * Created by zhangzl
 * 描述：用于封装返回的数据
 * 日期：  2016/12/5.
 */
public class Result<T> {
    /**
     * 0 操作成功
     */
    private int code = 2;
    private String msg = "操作失败";
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
