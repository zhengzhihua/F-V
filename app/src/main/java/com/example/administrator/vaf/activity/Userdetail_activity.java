package com.example.administrator.vaf.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.vaf.R;

/**
 * Created by admin on 2018/3/9 0009.
 */

public class Userdetail_activity extends AppCompatActivity{
    private TextView nametext;
    private TextView usertext;
    private TextView gendertext;
    private TextView phonenumtext;
    private TextView qqtext;
    private TextView roletext;
//    private String
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetail);
        init();

    }

    private void init() {
        nametext= (TextView) findViewById(R.id.nametext);
        usertext= (TextView) findViewById(R.id.usertext);
        gendertext= (TextView) findViewById(R.id.dendertext);
        phonenumtext= (TextView) findViewById(R.id.phonenumtext);
        qqtext= (TextView) findViewById(R.id.qqtext);
        roletext= (TextView) findViewById(R.id.roletext);
    }

}
