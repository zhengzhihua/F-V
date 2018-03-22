package com.example.administrator.vaf.model;

/**
 * Created by Administrator on 2017/12/19.
 */
public class User {

    private int id;  //用户id
    private String username;  //用户名
    private String userpwd;  //用户登录密码
    private String phone;  //用户手机号


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
