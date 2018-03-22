package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.vaf.R;

/**
 * Created by Administrator on 2018/3/21.
 */

public class Perfectuser_activity extends AppCompatActivity {
    private static final String TAG = "Perfectuser_activity";

    private Button saveuser;
    private EditText nametext,usertext,gendertext,phonetext,qqtext,roletext;
    private String username,role,phone,qq,name,gender;
    private String qqs,names,genders;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfectuser);
        initview();
        getdata();
        showdata();

    }

    private void gettextdata() {
        names=nametext.getText().toString();
        genders=gendertext.getText().toString();
        qqs=qqtext.getText().toString();

    }

    private void showdata() {
        nametext.setText(name);
        usertext.setText(username);
        gendertext.setText(gender);
        phonetext.setText(phone);
        qqtext.setText(qq);
        roletext.setText(name);


    }

    private void getdata() {
        Intent in = getIntent();
        Bundle bun = in.getExtras();
        username = bun.getString("username");
        gender = bun.getString("gender");

        phone = bun.getString("phone");
        qq = bun.getString("qq");
        name = bun.getString("name");
        String roles = bun.getString("role");
        if (roles.equals("1")) {
            role = "用户";
        } else if (roles.equals("2")) {
            role = "商家";
        }
    }

    private void initview() {
        saveuser.findViewById(R.id.saveuser);
        nametext.findViewById(R.id.nametexts);
        usertext.findViewById(R.id.usertexts);
        gendertext.findViewById(R.id.dendertexts);
        phonetext.findViewById(R.id.phonenumtexts);
        qqtext.findViewById(R.id.qqtexts);
        roletext.findViewById(R.id.roletexts);

        saveuser.setOnClickListener(saveuserlistener);

    }

    View.OnClickListener saveuserlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gettextdata();

        }
    };

}
