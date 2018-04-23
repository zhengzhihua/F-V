package com.example.administrator.vaf.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.database.OpenHelper;
import com.example.administrator.vaf.database.SqliteDB;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by admin on 2018/3/5 0005.
 */

public class Login_activity extends AppCompatActivity {
    private static final String TAG = "Login_activity";
    private static SqliteDB sqliteDB;
    private RelativeLayout reg;
    private Button login;
    private EditText count;
    private EditText pwd;
    private CheckBox checkbox;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String username,password,role,phone,qq,name,gender,userid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        initView();

    }


    protected void initView() {
        reg= (RelativeLayout) findViewById(R.id.regin);
        login= (Button) findViewById(R.id.login);
        count= (EditText) findViewById(R.id.count);
        pwd= (EditText) findViewById(R.id.pwd);
        checkbox=(CheckBox) findViewById(R.id.remember_password);
        boolean isremember=pref.getBoolean("remember_password",false);
        if(isremember){
            String username=pref.getString("username","");
            String password=pref.getString("password","");
            count.setText(username);
            pwd.setText(password);
            checkbox.setChecked(true);


        }

        reg.setOnClickListener(reglisten);
        login.setOnClickListener(loginlisten);

    }
    View.OnClickListener loginlisten=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
             username=count.getText().toString().trim();
             password=pwd.getText().toString().trim();

            login();
          /*  String sql="select * from users where username=? and userpwd=?";
            String[] strings=new String[]{username,password};
            Cursor clogin=sqliteDB.getInstance(Login_activity.this).search(sql,strings);
            if(clogin.moveToFirst()==true){
                editor=pref.edit();
                if(checkbox.isChecked()){       //检查复选框是否被选中
                    editor.putBoolean("remember_password",true);
                    editor.putString("username",username);
                    editor.putString("password",password);

                }else{
                    editor.clear();
                }
                editor.apply();
   //             String role=clogin.getString(clogin.getColumnIndex("role"));
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                //       bundle.putString("role",role);
                bundle.putString("role","用户");
                Intent intent=new Intent(Login_activity.this,Main_activity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }else
            if("".equals(username)||"".equals(password)){
                Toast.makeText(Login_activity.this,"用户名不能为空或密码不能为空",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Login_activity.this,"用户名不存在或密码错误",Toast.LENGTH_SHORT).show();
            }*/


        }
    };
    private void login() {

        Httpmanager.loginWithUsername(Login_activity.this,username,password, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {


                if(res!=null&& !res.equals("")){
                    editor=pref.edit();
                    if(checkbox.isChecked()){       //检查复选框是否被选中
                        editor.putBoolean("remember_password",true);
                        editor.putString("username",username);
                        editor.putString("password",password);

                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    ArrayList<Map<String, Object>> map=new ArrayList<Map<String,Object>>();
                    map =  res;
                    role= (String) map.get(0).get("role");
                    phone= (String) map.get(0).get("phone");
                    qq= (String) map.get(0).get("qq");
                    name= (String) map.get(0).get("name");
                    gender= (String) map.get(0).get("gender");
                    userid= (String) map.get(0).get("userid");
                    username= (String) map.get(0).get("username");

                    Bundle bundle=new Bundle();
                    bundle.putString("username",username);
                    bundle.putString("role",role);
                    bundle.putString("phone",phone);
                    bundle.putString("qq",qq);
                    bundle.putString("name",name);
                    bundle.putString("gender",gender);
                    bundle.putString("userid",userid);
                    Intent intent=new Intent(Login_activity.this,Main_activity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                if (data != null && !data.equals("")) {
                    Intent intent = new Intent(Login_activity.this, Main_activity.class);
                    startActivityForResult(intent, 0);

                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(Login_activity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }
    View.OnClickListener reglisten=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(Login_activity.this,Regist_activity.class);
            startActivity(intent);
        }
    };

}
