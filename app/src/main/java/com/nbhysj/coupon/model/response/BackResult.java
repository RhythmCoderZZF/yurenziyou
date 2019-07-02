package com.nbhysj.coupon.model.response;

/**
 * created by hysj on 2018/03/05.
 * description :  网络返回基类
 */
public class BackResult<T> {

    /**
     * code
     */
    private int code;

    /**
     * 成功或者失败返回消息
     */
    private String msg;

    /**
     * 数据体
     */
    private T data;

    //身份验证
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
