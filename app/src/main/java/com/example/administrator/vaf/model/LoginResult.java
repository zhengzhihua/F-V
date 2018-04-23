package com.example.administrator.vaf.model;

/**
 * Created by Administrator on 2018/3/20.
 */

public class LoginResult {

    private String success;//成功或失败标识
    private String message;//结果信息
    private Object object;//结果对象

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
