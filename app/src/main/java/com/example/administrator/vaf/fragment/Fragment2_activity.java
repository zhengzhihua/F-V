package com.example.administrator.vaf.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.activity.Addshopdetail_activity;
import com.example.administrator.vaf.adapter.Fragment2_adapter;
import com.example.administrator.vaf.adapter.Shophomeadapter;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/27.
 */

public class Fragment2_activity extends Fragment {

    private Button addbutton,deletebutton;
    private String username,role,phone,qq,name,gender,userid;
    private RecyclerView recyclerViews;
    private SwipeRefreshLayout swiperefreshlayout;
    private LinearLayoutManager layoutManagers;
    private ArrayList<Map<String, Object>> data,data1,data2;
    Context con;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bun=getArguments();
        username=bun.getString("username");
        role=bun.getString("role");
        userid=bun.getString("userid");
        phone=bun.getString("phone");
        qq=bun.getString("qq");
        name=bun.getString("name");
        gender=bun.getString("gender");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2_activity,container,false);
        con=view.getContext();
        initview(view);
        return view;
    }

    private void initview(View view) {
        addbutton= (Button) view.findViewById(R.id.addbutton);
        deletebutton= (Button) view.findViewById(R.id.deletebutton);
        swiperefreshlayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout1);
        deletebutton.setVisibility(View.INVISIBLE);
        recyclerViews= (RecyclerView) view.findViewById(R.id.recycleviews2);
        layoutManagers = new LinearLayoutManager(getActivity());   //++++++++++++++++++++++++++此处可能存在获取上下文
        layoutManagers.setOrientation(OrientationHelper.VERTICAL);    //设置布局是水平还是竖直
        recyclerViews.setLayoutManager(layoutManagers);
        if(role.equals("1")){
            addbutton.setVisibility(View.INVISIBLE);
            initdata1();
        }else if(role.equals("2")){
            addbutton.setVisibility(View.VISIBLE);
            initdata2();
        }
        //设置下拉进度条的背景颜色，默认白色
        swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置刷新圆圈的颜色，四种颜色变换
        swiperefreshlayout.setColorSchemeResources(R.color.progress1,R.color.progress2,R.color.holo_blue_light,R.color.holo_red_light);
        //刷新圆圈距离顶部的距离；
        swiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (role.equals("1")) {
                          /*  addbutton.setVisibility(View.INVISIBLE);*/
                            initdata1();
                        } else if (role.equals("2")) {
                           /* addbutton.setVisibility(View.VISIBLE);*/
                            initdata2();
                        }
                        swiperefreshlayout.setRefreshing(false);
                        Toast.makeText(con, "更新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 600);
            }
        });
        addbutton.setOnClickListener(addbuttonlistener);
        deletebutton.setOnClickListener(deletebuttonlistener);




    }
    View.OnClickListener addbuttonlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getActivity(),Addshopdetail_activity.class);
            Bundle bundle=new Bundle();
            bundle.putString("userid",userid);
            bundle.putString("username",username);
            intent.putExtras(bundle);

            startActivity(intent);

        }
    };
    View.OnClickListener deletebuttonlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private void initdata1() {
        String where="userids='"+userid+"'";
        String word="shopcarid,shopid,shopname,shopdescribe,username,userid,price,type,produceplace,image,usernames,userids";
        //     String word="shopid,shopname,shopdescribe,username,userid,price,type,produceplace";
        Httpmanager.selectdata(getActivity(), "shopcar", word, where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {

                if(!res.equals("")&&res!=null){
                    data1=res;
//                            shophomeadapter.updata(data1);
//                            recycleview.setAdapter(shophomeadapter);
//                            linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);   //++++++++++++++++++++++++++此处可能存在获取上下文
//
//                            recycleview.setLayoutManager(linearLayoutManager);
                    Fragment2_adapter fragment2_adapter= new Fragment2_adapter(con, data1,role);
                    recyclerViews.setAdapter(fragment2_adapter);
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
    private void initdata2() {
        String where="userid='"+userid+"'";
        String word="shopid,shopname,shopdescribe,username,userid,price,type,produceplace,image";
        //     String word="shopid,shopname,shopdescribe,username,userid,price,type,produceplace";
        Httpmanager.selectdata(getActivity(), "commodity_bank", word, where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {

                if(!res.equals("")&&res!=null){
                    data2=res;
//                            shophomeadapter.updata(data1);
//                            recycleview.setAdapter(shophomeadapter);
//                            linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);   //++++++++++++++++++++++++++此处可能存在获取上下文
//
//                            recycleview.setLayoutManager(linearLayoutManager);
                    Fragment2_adapter fragment2_adapter= new Fragment2_adapter(con, data2,role);
                    recyclerViews.setAdapter(fragment2_adapter);
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
