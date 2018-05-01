package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.adapter.Beforecriticism_adapter;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Beforecriticism_activity extends AppCompatActivity{
    private static final String TAG="Beforecriticism_activity";
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swiperefreshlayout;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Map<String,Object>> lists;
    private String status,userid,userids,role;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_same);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
         role=bundle.getString("role");
        userids=bundle.getString("userid");
        if(role.equals("1")){
            userid="clientid";
        }else if(role.equals("2")){
            userid="userid";
        }
        getdata();
        recyclerView= (RecyclerView) findViewById(R.id.recycleview2);
        swiperefreshlayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout2);
        //设置下拉进度条的背景颜色，默认白色
        swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置刷新圆圈的颜色，四种颜色变换
        swiperefreshlayout.setColorSchemeResources(R.color.progress1,R.color.progress2,R.color.holo_blue_light,R.color.holo_red_light);
        //刷新圆圈距离顶部的距离；
        swiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        linearLayoutManager=new LinearLayoutManager(Beforecriticism_activity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getdata();

                        swiperefreshlayout.setRefreshing(false);
                        Toast.makeText(Beforecriticism_activity.this, "更新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 600);
            }
        });


    }

    private void getdata() {
        String word="orderformid,orderdetail,username,shopid,shopname,userid,status,image,price,clientname,clientid,number";
        String where=userid+"='"+ userids+"'and number='1'and status='订单完成'";
        Httpmanager.selectdata(Beforecriticism_activity.this, "orderform",word,where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {
                if(!res.equals("")&&res!=null){
                    lists=res;
                    Beforecriticism_adapter beforecriticism_adapter=new Beforecriticism_adapter(Beforecriticism_activity.this,lists,role);
                    recyclerView.setAdapter(beforecriticism_adapter);
                }else{
                    Toast.makeText(Beforecriticism_activity.this,"暂无数据",Toast.LENGTH_LONG).show();
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
}
