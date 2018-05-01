package com.example.administrator.vaf.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.adapter.Shophomeadapter;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * Created by Administrator on 2017/12/27.
 */

public class Fragment1_activity extends Fragment implements AdapterView.OnItemClickListener{
    private static final String TAG ="Fragment1_activity";
    private EditText exploreframe;
    private ImageView exploreimage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swiperefreshlayout;
    private RecyclerView recycleview;
    private Shophomeadapter shophomeadapter;
    private String username,role,phone,qq,name,gender,userid;
    private ArrayList<Map<String, Object>> data,data1,data2;
    private String exploreframetext;
    private Context con;
    private  String wheres="";
    private GridView gridView;
    private List<Map<String,Object>> dataList;
    private int[] icon={R.drawable.fruit,R.drawable.vegetable,R.drawable.anyone};
    private String[] iconName={"水果","蔬菜","全部"};
    private SimpleAdapter adapter;
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
        gridView=(GridView)view.findViewById(R.id.gridView);
        swiperefreshlayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recycleview= (RecyclerView) view.findViewById(R.id.recycleview1);
/*
        1.准备数据源； 2.新建适配器； 3.GridView加载适配器；4.GridView配置事件监听器
         */
        dataList=new ArrayList<>();
        adapter=new SimpleAdapter(con,getData(),R.layout.item,new String[]{"image","text"},
                new int[]{R.id.image,R.id.text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
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
        swiperefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        //设置刷新圆圈的颜色，四种颜色变换
        swiperefreshlayout.setColorSchemeResources(R.color.progress1,R.color.progress2,R.color.holo_blue_light,R.color.holo_red_light);
        //刷新圆圈距离顶部的距离；
        swiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
//            linearLayoutManager = new LinearLayoutManager(getContext());   //++++++++++++++++++++++++++此处可能存在获取上下文
//            linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//            recycleview.setLayoutManager(linearLayoutManager);
//             shophomeadapter = new Shophomeadapter(getActivity(), data);
//            recycleview.setAdapter(shophomeadapter);



        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initdata();

                        swiperefreshlayout.setRefreshing(false);
                        Toast.makeText(con, "更新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 600);


            }
        });

    }
    private List<Map<String,Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    private void initdata() {
        String where=wheres;
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
       /* String friststr=exploreframetext.substring(0,)*/
       if(!exploreframetext.equals("")){

           String startstr=exploreframetext.substring(0,1);
           String finalstr=exploreframetext.substring(exploreframetext.length()-1,exploreframetext.length()); //获取字符串最后一个字符
       //    String finalstr=exploreframetext.substring(-1);     //第二种方法获取字符串最后一个字符
           String where="shopname like'%"+startstr+"%'or shopname like'"+finalstr+"'";


        String word="shopid,shopname,shopdescribe,username,userid,price,type,produceplace,image";

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
       }else{
           Toast.makeText(con,"搜索的商品名称不能为空",Toast.LENGTH_LONG).show();
       }

    }
};

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(con,"我是"+iconName[position],Toast.LENGTH_SHORT).show();
       if(iconName[position].equals("水果")){
           wheres="type='水果'";
           initdata();
       }
        else if(iconName[position].equals("蔬菜")){
           wheres="type='蔬菜'";
           initdata();
       }else if(iconName[position].equals("全部")){
           wheres="";
           initdata();
       }
    }
}
