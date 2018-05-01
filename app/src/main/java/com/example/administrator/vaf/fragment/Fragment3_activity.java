package com.example.administrator.vaf.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.activity.Address_activity;
import com.example.administrator.vaf.activity.Allorderform_activity;
import com.example.administrator.vaf.activity.Beforecriticism_activity;
import com.example.administrator.vaf.activity.Dealorderform_activity;
import com.example.administrator.vaf.activity.Main_activity;
import com.example.administrator.vaf.activity.Perfectuser_activity;
import com.example.administrator.vaf.activity.Shopdetail_activity;
import com.example.administrator.vaf.activity.Userdetail_activity;
import com.example.administrator.vaf.adapter.Criticism_adapter;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;

import java.util.ArrayList;
import java.util.Map;

import static android.R.attr.data;
import static android.content.ContentValues.TAG;
import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Created by Administrator on 2017/12/27.
 */

public class Fragment3_activity extends Fragment {

    private LinearLayout userinformation;   //用户信息
    private TextView users;
    private TextView Full_order;       //全部订单
    private TextView Transaction_order;   //交易订单
    private TextView evaluated;    //待评价
    private ImageView address;               //地址
    private TextView balance;        //余额
    private ImageView write_information;     //完善信息
    private Button Exit_logon;               //退出登录
    private String username,role,phone,qq,name,gender,userid;
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
        View view=inflater.inflate(R.layout.fragment3_activity,container,false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        userinformation= (LinearLayout) view.findViewById(R.id.user);
        users= (TextView) view.findViewById(R.id.users);
        users.setText(username);
        Full_order= (TextView) view.findViewById(R.id.Full_order);
        Transaction_order= (TextView) view.findViewById(R.id.Transaction_order);
        evaluated= (TextView) view.findViewById(R.id.evaluated);
        if(role.equals("2")){
            evaluated.setVisibility(View.INVISIBLE);
        }
        address= (ImageView) view.findViewById(R.id.address);
        write_information= (ImageView) view.findViewById(R.id.write_information);
        Exit_logon= (Button) view.findViewById(R.id.Exit_logon);
        balance= (TextView) view.findViewById(R.id.balance);
        getdata();
        Exit_logon.setOnClickListener(Exit_logonlistener);
        write_information.setOnClickListener(write_informationlistener);
        address.setOnClickListener(addresslistener);
        evaluated.setOnClickListener(evaluatedlistener);
        Transaction_order.setOnClickListener(Transaction_orderlistener);
        Full_order.setOnClickListener(Full_orderlistener);
        userinformation.setOnClickListener(userinformationlistener);
    }
//退出
    View.OnClickListener Exit_logonlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    };
//用户信息完善
    View.OnClickListener write_informationlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(getActivity(),Perfectuser_activity.class);
            Bundle bumdle=new Bundle();
            bumdle.putString("username",username);
            bumdle.putString("role",role);
            bumdle.putString("phone",phone);
            bumdle.putString("qq",qq);
            bumdle.putString("name",name);
            bumdle.putString("gender",gender);
            bumdle.putString("userid",userid);
            intent.putExtras(bumdle);
            startActivity(intent);
        }
    };
    //地址
    View.OnClickListener addresslistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getActivity(), Address_activity.class);
            Bundle bumdle=new Bundle();
            bumdle.putString("username",username);
            bumdle.putString("role",role);
            bumdle.putString("phone",phone);

            bumdle.putString("userid",userid);
            intent.putExtras(bumdle);
            startActivity(intent);


        }
    };
    //待评价
    View.OnClickListener evaluatedlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(getActivity(),Beforecriticism_activity.class);
            Bundle bumdle=new Bundle();
            bumdle.putString("username",username);
            bumdle.putString("role",role);
            bumdle.putString("userid",userid);
            intent.putExtras(bumdle);
            startActivity(intent);
        }
    };
//用户信息
    View.OnClickListener userinformationlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Intent();
            intent.setClass(getActivity(),Userdetail_activity.class);
            Bundle bumdle=new Bundle();
            bumdle.putString("username",username);
            bumdle.putString("role",role);
            bumdle.putString("phone",phone);
            bumdle.putString("qq",qq);
            bumdle.putString("userid",userid);
            bumdle.putString("name",name);
            bumdle.putString("gender",gender);
            intent.putExtras(bumdle);
            startActivity(intent);
        }
    };
    //全部订单
    View.OnClickListener Full_orderlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(getActivity(),Allorderform_activity.class);
            Bundle bumdle=new Bundle();
            bumdle.putString("username",username);
            bumdle.putString("userid",userid);
            bumdle.putString("role",role);
            intent.putExtras(bumdle);
            startActivity(intent);
        }
    };
//交易订单
    View.OnClickListener Transaction_orderlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(getActivity(),Dealorderform_activity.class);
            Bundle bumdle=new Bundle();
            bumdle.putString("username",username);
            bumdle.putString("userid",userid);
            bumdle.putString("role",role);
            intent.putExtras(bumdle);
            startActivity(intent);
        }
    };
    private void getdata(){
    String where="userid='"+userid+"'";
    String word="money";
    Httpmanager.selectdata1(getActivity(), "money", word, where, new HttpRequestHandler<String>() {
        @Override
        public void onSuccess(String data) {

        }


        @Override
        public void onSuccesss(ArrayList<Map<String, Object>> res) {

            if (!res.equals("") && res != null) {
                balance.setText((String) res.get(0).get("money"));
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
