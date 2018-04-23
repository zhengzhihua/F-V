package com.example.administrator.vaf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.vaf.R;

/**
 * Created by admin on 2018/3/9 0009.
 */

public class Userdetail_activity extends AppCompatActivity{

    private static final String TAG = "Userdetail_activity";

    private TextView nametext;
    private TextView usertext;
    private TextView gendertext;
    private TextView phonenumtext;
    private TextView qqtext;
    private TextView roletext;
    private String username,role,phone,qq,name,gender;
//    private String
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);
        init();
        getdata();
        showdata();
    }

    private void init() {
        nametext= (TextView) findViewById(R.id.nametext);
        usertext= (TextView) findViewById(R.id.usertext);
        gendertext= (TextView) findViewById(R.id.dendertext);
        phonenumtext= (TextView) findViewById(R.id.phonenumtext);
        qqtext= (TextView) findViewById(R.id.qqtext);
        roletext= (TextView) findViewById(R.id.roletext);
    }
    protected void getdata() {
        Intent in=getIntent();
        Bundle bun=in.getExtras();
        username=bun.getString("username");
        gender=bun.getString("gender");

        phone=bun.getString("phone");
        qq=bun.getString("qq");
        name=bun.getString("name");
        String roles=bun.getString("role");
        if(roles.equals("1")){
            role="用户";
        }else if(roles.equals("2")){
            role="商家";
        }





    }
    protected void showdata() {
        nametext.setText(name);
        usertext.setText(username);
        gendertext.setText(gender);
        phonenumtext.setText(phone);
        qqtext.setText(qq);
        roletext.setText(role);
    }

}
