package com.example.administrator.vaf.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.adapter.Shophomeadapter;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;

import java.util.ArrayList;
import java.util.Map;




/**
 * Created by Administrator on 2017/12/27.
 */

public class Fragment1_activity extends Fragment {
    private static final String TAG ="Fragment1_activity";
    private EditText exploreframe;
    private ImageView exploreimage;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swiperefreshlayout;
    private RecyclerView recycleview;
    private Shophomeadapter shophomeadapter;
    private String username,role,phone,qq,name,gender,userid;
    private ArrayList<Map<String, Object>> data,data1,data2;
    String exploreframetext;
    Context con;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
        View view=inflater.inflate(R.layout.fragment1_activity,container,false);
         con=view.getContext();
        initview(view);
        return view;
    }

    private void initview(View view) {
        exploreframe= (EditText) view.findViewById(R.id.write_explore);
        exploreimage= (ImageView) view.findViewById(R.id.explore_button);
 //       swiperefreshlayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshlayout);
        recycleview= (RecyclerView) view.findViewById(R.id.recycleview1);

        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);   //++++++++++++++++++++++++++此处可能存在获取上下文

        recycleview.setLayoutManager(linearLayoutManager);

        initdata();

        exploreimage.setOnClickListener(exploreimagelistener);
//            if (exploreframetext.equals("") && exploreframetext == null) {
//
//                data = data1;
//            } else {
//                exploreimage.setOnClickListener(exploreimagelistener);
//                data = data2;
//
//        }
            //设置下拉进度条的背景颜色，默认白色
//        swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
//        //设置刷新圆圈的颜色，四种颜色变换
//        swiperefreshlayout.setColorSchemeResources(R.color.progress1,R.color.progress2,R.color.holo_blue_light,R.color.holo_red_light);
//        //刷新圆圈距离顶部的距离；
//        swiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
//                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
//                        .getDisplayMetrics()));
//            linearLayoutManager = new LinearLayoutManager(getContext());   //++++++++++++++++++++++++++此处可能存在获取上下文
//            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//            recycleview.setLayoutManager(linearLayoutManager);
//             shophomeadapter = new Shophomeadapter(getActivity(), data);
//            recycleview.setAdapter(shophomeadapter);



  //      swiperefreshlayout.setOnRefreshListener(swiperefreshlayoutlistener);

    }

    private void initdata() {
        String where="";
        String word="shopid,shopname,shopdescribe,username,userid,price,type,produceplace,image";
   //     String word="shopid,shopname,shopdescribe,username,userid,price,type,produceplace";
        Httpmanager.selectdata(getActivity(), "commodity_bank", word, where, new HttpRequestHandler<String>() {
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
                            shophomeadapter = new Shophomeadapter(con, data1,userid,username,role);
                            recycleview.setAdapter(shophomeadapter);
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


 /*   SwipeRefreshLayout.OnRefreshListener swiperefreshlayoutlistener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            String where="";
            String word="";
            Httpmanager.selectdata(getActivity(), "", word, where, new HttpRequestHandler<String>() {
                @Override
                public void onSuccess(String data) {

                }

                @Override
                public void onSuccesss(ArrayList<Map<String, Object>> res) {

                    if(!res.equals("")&&res!=null){
                        data=res;
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
    };*/

//查询监听
    View.OnClickListener exploreimagelistener=new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       /* Httpmanager.selectdata(getActivity());*/
        exploreframetext=exploreframe.getText().toString();
        String word="shopid,shopname,shopdescribe,username,userid,price,type,produceplace,image";
        String where="type='"+exploreframetext+"'";
        Httpmanager.selectdata(getActivity(), "commodity_bank", word, where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {

                if(!res.equals("")&&res!=null){
                    data2=res;
//                    linearLayoutManager = new LinearLayoutManager(getContext());   //++++++++++++++++++++++++++此处可能存在获取上下文
//                    linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//                    recycleview.setLayoutManager(linearLayoutManager);
                    shophomeadapter = new Shophomeadapter(getActivity(), data2,userid,username,role);
                    recycleview.setAdapter(shophomeadapter);
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
};

}
