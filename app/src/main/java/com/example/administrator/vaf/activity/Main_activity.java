package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.administrator.vaf.R;
import com.example.administrator.vaf.fragment.Fragment1_activity;
import com.example.administrator.vaf.fragment.Fragment2_activity;
import com.example.administrator.vaf.fragment.Fragment3_activity;

/**
 * Created by Administrator on 2017/12/20.
 */

public class Main_activity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout homepage,shoppingcar,personal;
    private Fragment1_activity homefragement;
    private Fragment2_activity shoppingfragment;
    private Fragment3_activity personalfragment;
    private ImageView imageView;
    private FrameLayout mVp;
    private String username;
    private String role;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById();
        getdata();
        initfragment1();

    }
    protected void getdata() {
        Intent in = getIntent();
        Bundle bun = in.getExtras();
        username = bun.getString("username");
        role = bun.getString("role");

    }


    private void initfragment1() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        if(homefragement==null){
            homefragement=new Fragment1_activity();
            transaction.add(R.id.mVp,homefragement);
        }
        hideAllFragment(transaction);   //影藏所有的fragment；
        transaction.show(homefragement);   //显示要显示的fragment；
        transaction.commit();
    }
    private void initfragment2() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        if(shoppingfragment==null){
            shoppingfragment=new Fragment2_activity();
            transaction.add(R.id.mVp,shoppingfragment);
        }
        hideAllFragment(transaction);   //影藏所有的fragment；
        transaction.show(shoppingfragment);   //显示要显示的fragment；
        transaction.commit();
    }
    private void initfragment3() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        if(personalfragment==null){
            personalfragment=new Fragment3_activity();
            Bundle bund=new Bundle();
            bund.putString("username",username);
            bund.putString("role",role);
            personalfragment.setArguments(bund);

            transaction.add(R.id.mVp,personalfragment);
        }
        hideAllFragment(transaction);   //影藏所有的fragment；
        transaction.show(personalfragment);   //显示要显示的fragment；
        transaction.commit();
    }



    protected void findViewById() {
        homepage= (LinearLayout) findViewById(R.id.homepage);
        shoppingcar= (LinearLayout) findViewById(R.id.shoppingcar);
        personal= (LinearLayout) findViewById(R.id.personal);
        mVp= (FrameLayout) findViewById(R.id.mVp);
        imageView= (ImageView) findViewById(R.id.changeimage);
        homepage.setOnClickListener(this);
        shoppingcar.setOnClickListener(this);
        personal.setOnClickListener(this);

    }


    private void hideAllFragment(FragmentTransaction fTransaction) {

        if (homefragement != null) fTransaction.hide(homefragement);
        if (shoppingfragment != null) fTransaction.hide(shoppingfragment);
        if (personalfragment != null) fTransaction.hide(personalfragment);
    }

    @Override
    public void onClick(View v) {
        if(v==homepage){
            initfragment1();
        }
        if(v==shoppingcar){
            initfragment2();
        }
        if(v==personal){
            initfragment3();
        }
    }


}
