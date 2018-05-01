package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.UUIDGenerator;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/2.
 */

public class Criticism_detail extends AppCompatActivity{
    private static final String TAG="Criticism_detail";
    private EditText criticismtext;
    private Button criticismbutton;
    private Bundle bun;
    private String criticism,role,userids;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criticismdetail);
        Intent in=getIntent();
        bun=in.getExtras();
        role=bun.getString("role");
        userids=bun.getString("shopuserid");

        initview();
    }

    private void initview() {
        criticismtext= (EditText) findViewById(R.id.criticism1);

        criticismbutton= (Button) findViewById(R.id.criticismbutton);
        criticismbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String criticismid= UUIDGenerator.getUUID();
                criticism=criticismtext.getText().toString().trim();
                String word="(criticismid,shopid,shopname,shopusername,shopuserid,storeuserid,criticismdetail)";
                String value="('"+criticismid+"','"+bun.getString("shopid")+"','"+bun.getString("shopname")+"','"+bun.getString("shopusername")+"','"+bun.getString("shopuserid")+"','"+bun.getString("storeuserid")+"','"+criticism+"')";
                Httpmanager.insertdata(Criticism_detail.this, "", "", "criticism",word ,value,new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(Criticism_detail.this,"评论成功",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccesss(ArrayList<Map<String, Object>> res) {

                    }

                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {

                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
                String sets="number='2'";
                String wheres="orderformid='"+bun.getString("orderformid")+"'";
                Httpmanager.updata(Criticism_detail.this, "orderform", sets, wheres, new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(Criticism_detail.this,"评论成功",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccesss(ArrayList<Map<String, Object>> res) {

                    }

                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {

                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
                Intent intent=new Intent(Criticism_detail.this,Beforecriticism_activity.class);
                Bundle bundle=new Bundle();
                bundle.putString("role",role);
                bundle.putString("userid",userids);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}
