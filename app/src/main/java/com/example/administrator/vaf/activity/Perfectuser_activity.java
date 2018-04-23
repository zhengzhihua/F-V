package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/21.
 */

public class Perfectuser_activity extends AppCompatActivity {
    private static final String TAG = "Perfectuser_activity";

    private Button saveuser;
    private EditText nametext,usertext,gendertext,phonetext,qqtext,roletext;
    private String username,role,phone,qq,name,gender,userid;
    private String qqs,names,genders,mes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfectuser);
        getdata();
        initview();
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
        userid=bun.getString("userid");
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
        saveuser= (Button) findViewById(R.id.saveuser);
        nametext= (EditText) findViewById(R.id.nametexts);
        usertext= (EditText) findViewById(R.id.usertexts);
        gendertext= (EditText) findViewById(R.id.dendertexts);
        phonetext= (EditText) findViewById(R.id.phonenumtexts);
        qqtext= (EditText) findViewById(R.id.qqtexts);
        roletext= (EditText) findViewById(R.id.roletexts);
        gettextdata();
        saveuser.setOnClickListener(saveuserlistener);

    }

    View.OnClickListener saveuserlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gettextdata();
            String set="name='"+names+"',qq='"+qqs+"',gender='"+genders+"'";
            String where="userid='"+userid +"'";
            Httpmanager.updata(Perfectuser_activity.this, "user", set, where, new HttpRequestHandler<String>() {
                @Override
                public void onSuccess(String data) {
                     mes=data;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Perfectuser_activity.this,mes,Toast.LENGTH_LONG).show();
                        }
                    },1000);


                }

                @Override
                public void onSuccesss(ArrayList<Map<String, Object>> res) {

                }

                @Override
                public void onSuccess(String data, int totalPages, int currentPage) {

                }

                @Override
                public void onFailure(String error) {
                    Toast.makeText(Perfectuser_activity.this,error,Toast.LENGTH_LONG).show();
                }
            });

        }
    };

}
