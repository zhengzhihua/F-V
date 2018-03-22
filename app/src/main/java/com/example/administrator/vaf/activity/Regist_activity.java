package com.example.administrator.vaf.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.database.OpenHelper;
import com.example.administrator.vaf.database.SqliteDB;
import com.example.administrator.vaf.design.Phone;
import com.example.administrator.vaf.design.UUIDGenerator;

/**
 * Created by Administrator on 2017/12/26.
 */

public class Regist_activity extends AppCompatActivity {
    private static SqliteDB sqlitedb;
    private EditText counttext;
    private  EditText usernametext;
    private  EditText passwordtext;
    private  EditText twopswtext;
    private RadioButton userbutton;
    private RadioButton sellerbutton;
    private Button registbutton;
    private String role;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        findViewById();
    }
    protected void findViewById(){
        counttext=(EditText)findViewById(R.id.count);
        usernametext=(EditText)findViewById(R.id.username);
        passwordtext=(EditText)findViewById(R.id.password);
        twopswtext=(EditText)findViewById(R.id.twopsw);
        userbutton=(RadioButton)findViewById(R.id.userbutton);
        sellerbutton=(RadioButton)findViewById(R.id.sellerbutton);
        registbutton=(Button)findViewById(R.id.login);
        beforeinitview();
        registbutton.setOnClickListener(registbuttonlisten);
    }
    protected void beforeinitview(){

    }

    View.OnClickListener registbuttonlisten=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phone=counttext.getText().toString().trim();
            String password=passwordtext.getText().toString().trim();
            String username=usernametext.getText().toString().trim();
            String twopsw=twopswtext.getText().toString().trim();
            if(userbutton.isChecked()){
                role="用户";
            }
            if(sellerbutton.isChecked()){
                role="商户";
            }

            String Sql="select * from users where username=?";
            String[] strings=new String[]{username};
            Cursor clogin=sqlitedb.getInstance(Regist_activity.this).search(Sql,strings);
            if(clogin.getCount()>0){
                Toast.makeText(Regist_activity.this,"该用户已经存在",Toast.LENGTH_LONG).show();
            }else
            if( !Phone.isValidMobiNumber(phone)){
                Toast.makeText(Regist_activity.this,"请输入正确的手机号",Toast.LENGTH_LONG).show();

            }else
            if(!password.equals(twopsw)){
                Toast.makeText(Regist_activity.this,"两次输入的密码不一致",Toast.LENGTH_LONG).show();
            }else

            if(!phone.equals("") && !username.equals("") && !"".equals(password) && !"".equals(twopsw) && !"".equals(role)){
                String userid= UUIDGenerator.getUUID();
                String sql="INSERT INTO users(userid,username,userpwd,phone,role) values(?,?,?,?,?)";
                String[] string=new String[]{userid,username,password,phone,role};
               /* db.execSQL("insert into User(userid,username,userpwd,phone,role) values(?,?,?,?,?) ", new String[]{userid,username,password,phone,role});*/
                sqlitedb.getInstance(Regist_activity.this).insert(sql,string);

              /*  Intent intent = new Intent(Regist_activity.this, Login_activity.class);
                startActivity(intent);*/
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Regist_activity.this, "注册成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Regist_activity.this, Login_activity.class);
                        startActivity(intent);

                    }
                },1000);


            }else{
                Toast.makeText(Regist_activity.this,"存在输入为空或未选择",Toast.LENGTH_LONG).show();
            }
 /*        new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        Intent intent = new Intent(Regist_activity.this, Login_activity.class);
                        startActivity(intent);
                        Toast.makeText(Regist_activity.this, "注册成功", Toast.LENGTH_LONG).show();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();*/
        }
    };
}
