package com.example.administrator.vaf.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.activity.Main_activity;
import com.example.administrator.vaf.activity.Userdetail_activity;

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
    private ImageView write_information;     //完善信息
    private Button Exit_logon;               //退出登录
    private String username;
    private String role;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bun=getArguments();
        username=bun.getString("username");
        role=bun.getString("role");

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
        address= (ImageView) view.findViewById(R.id.address);
        write_information= (ImageView) view.findViewById(R.id.write_information);
        Exit_logon= (Button) view.findViewById(R.id.Exit_logon);
        Exit_logon.setOnClickListener(Exit_logonlistener);
        write_information.setOnClickListener(write_informationlistener);
        address.setOnClickListener(addresslistener);
        evaluated.setOnClickListener(evaluatedlistener);
        Transaction_order.setOnClickListener(Transaction_orderlistener);
        Full_order.setOnClickListener(Full_orderlistener);
        userinformation.setOnClickListener(userinformationlistener);
    }

    View.OnClickListener Exit_logonlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    };

    View.OnClickListener write_informationlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener addresslistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener evaluatedlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener userinformationlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Intent();
            intent.setClass(getActivity(),Userdetail_activity.class);
            Bundle bumdle=new Bundle();
            bumdle.putString("username",username);
            bumdle.putString("role",role);
           intent.putExtras(bumdle);
            startActivity(intent);
        }
    };
    View.OnClickListener Full_orderlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener Transaction_orderlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
