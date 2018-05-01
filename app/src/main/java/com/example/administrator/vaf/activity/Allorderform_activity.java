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
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.adapter.Allorderform_adapter;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Allorderform_activity extends AppCompatActivity{
    private static final String TAG="Allorderform_activity";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swiperefreshlayout;
    private ArrayList<Map<String,Object>> lists;
    private String where;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_same);
        Intent intent=getIntent();
        Bundle bun=intent.getExtras();
        String role=bun.getString("role");
        Log.e(TAG,  role);
        if(role.equals("1")){
            where="clientid='"+bun.getString("userid")+"'";
        }else if(role.equals("2")&&role=="2"){
            where="userid='"+bun.getString("userid")+"'";
        }
        getdata();
        recyclerView= (RecyclerView) findViewById(R.id.recycleview2);
        swiperefreshlayout= (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout2);
        //设置下拉进度条的背景颜色，默认白色
        swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置刷新圆圈的颜色，四种颜色变换
        swiperefreshlayout.setColorSchemeResources(R.color.progress1,R.color.progress2,R.color.holo_blue_light,R.color.holo_red_light);
        //刷新圆圈距离顶部的距离；
        swiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        linearLayoutManager=new LinearLayoutManager(Allorderform_activity.this);
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
                        Toast.makeText(Allorderform_activity.this, "更新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 600);
            }
        });


    }
    private void getdata() {
        String word="shopid,shopname,shopdescribe,username,userid,shopname,price,type,produceplace,image,clientname,clientid";


        Httpmanager.selectdata(Allorderform_activity.this, "orderform", word, where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {
                if(!res.equals("")&&res!=null){
                    lists=res;
                    Allorderform_adapter allorderform_adapter=new Allorderform_adapter(Allorderform_activity.this,lists);
                    recyclerView.setAdapter(allorderform_adapter);
                }else{
                    Toast.makeText(Allorderform_activity.this,"暂无数据",Toast.LENGTH_LONG).show();
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
