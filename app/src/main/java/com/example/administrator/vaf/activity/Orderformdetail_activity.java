package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/12.
 */

public class Orderformdetail_activity extends AppCompatActivity{
    private static final String TAG = "Orderformdetail_activity";
    private TextView adress_text,orderformstatus,startorderform,finishorderform,shopname_text,simpleprice,total_text,shangjia,shangjiaphone,user_text,userphone;
    private String adress,orderstatus,startform,finishform,shopname,simpleprices,totalprice,shangjianame,shangjianphones,username,userphones,userid,shangjiaid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderformdetail);
        findViewById();
        getdate();
        showdata();
    }
    private void findViewById(){
        adress_text= (TextView) findViewById(R.id.adress_text);
        orderformstatus= (TextView) findViewById(R.id.orderformstatus);
        startorderform= (TextView) findViewById(R.id.startorderform);
        finishorderform= (TextView) findViewById(R.id.finishorderform);
        shopname_text= (TextView) findViewById(R.id.shop_text);
        simpleprice= (TextView) findViewById(R.id.simpleprice);
        total_text= (TextView) findViewById(R.id.total_text);
        shangjia= (TextView) findViewById(R.id.shangjia);
        shangjiaphone= (TextView) findViewById(R.id.shangjiaphone);
        user_text= (TextView) findViewById(R.id.user_text);
        userphone= (TextView) findViewById(R.id.userphone);

    }
    private void getdate(){
        Intent iet=getIntent();
        Bundle bun=iet.getExtras();
        adress=bun.getString("adressdetail");
        orderstatus=bun.getString("status");
        startform=bun.getString("startdatetime");
        finishform=bun.getString("finishtime");
        shopname=bun.getString("shopname");
        simpleprices=bun.getString("price");
        totalprice=bun.getString("totalmoney");
        shangjianame=bun.getString("username");
        username=bun.getString("clientname");
        userid=bun.getString("clientid");
        shangjiaid=bun.getString("userid");
        getdate1();
        getdate2();
    }
    private void getdate1(){
        String word="phone";
        String where="userid='"+shangjiaid+"'";
        Httpmanager.selectdata1(Orderformdetail_activity.this, "user", word, where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }


            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {

                if (!res.equals("")) {
                    String shangjianphones= (String) res.get(0).get("phone");
                    shangjiaphone.setText(shangjianphones);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {

            }
        });

    }
    private void getdate2(){
        String word="phone";
        String where="userid='"+userid+"'";
        Httpmanager.selectdata1(Orderformdetail_activity.this, "user", word, where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }


            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {

                if (!res.equals("") && res != null) {
                   String userphones= (String) res.get(0).get("phone");
                    userphone.setText(userphones);
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
    private void showdata(){
        orderformstatus.setText(orderstatus);
        adress_text.setText(adress);
        startorderform.setText(startform);
        finishorderform.setText(finishform);
        shopname_text.setText(shopname);
        simpleprice.setText(simpleprices);
        total_text.setText(totalprice);
        shangjia.setText(shangjianame);

        user_text.setText(username);


    }
}
