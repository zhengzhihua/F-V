package com.example.administrator.vaf.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


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
    private android.app.FragmentManager fragmanager;
    private FrameLayout mVp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        fragmanager=getFragmentManager();
        findViewById();

    }


    protected void findViewById() {
        homepage= (LinearLayout) findViewById(R.id.homepage);
        shoppingcar= (LinearLayout) findViewById(R.id.shoppingcar);
        personal= (LinearLayout) findViewById(R.id.personal);
        mVp= (FrameLayout) findViewById(R.id.mVp);
        homepage.setOnClickListener(this);
        shoppingcar.setOnClickListener(this);
        personal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction=fragmanager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId())
        {
            case R.id.homepage:
                setSelected();
                homepage.setSelected(true);
                if(homefragement==null)
                {
                    homefragement=new Fragment1_activity();
                    fTransaction.add(R.id.mVp,homefragement);
                }else{
                    fTransaction.show(homefragement);
                }
                break;
            case R.id.shoppingcar:
                setSelected();
                shoppingcar.setSelected(true);
                if(shoppingfragment==null)
                {
                    shoppingfragment=new Fragment2_activity();
                    fTransaction.add(R.id.mVp,shoppingfragment);
                }else{
                    fTransaction.show(shoppingfragment);
                }
                break;
            case R.id.personal:
                setSelected();
                personal.setSelected(true);
                if(personalfragment==null)
                {
                    personalfragment=new Fragment3_activity();
                    fTransaction.add(R.id.mVp,personalfragment);
                }else{
                    fTransaction.show(personalfragment);
                }
                break;



        }
        fTransaction.commit();
    }

    //重置所有文本的选中状态
    private void setSelected() {
        homepage.setSelected(false);
        shoppingcar.setSelected(false);
        personal.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fTransaction) {

        if (homefragement != null) fTransaction.hide(homefragement);
        if (shoppingfragment != null) fTransaction.hide(shoppingfragment);
        if (personalfragment != null) fTransaction.hide(personalfragment);
    }

}
