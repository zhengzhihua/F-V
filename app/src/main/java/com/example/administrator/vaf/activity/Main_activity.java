package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private static final String TAG = "Main_activity";
    private LinearLayout homepage,shoppingcar,personal;
    private Fragment1_activity homefragement;
    private Fragment2_activity shoppingfragment;
    private Fragment3_activity personalfragment;
    private FragmentTransaction transaction;
    private ImageView imageView,homeimage,personimage;
    private FrameLayout mVp;
   /* private String id=new String();*/
    private String username,role,phone,qq,name,gender,userid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Intent intents=getIntent();

        getdata();

        findViewById();
        if(role.equals("2")){
            imageView.setImageResource(R.drawable.addition);
        }else if(role.equals("1")){
            imageView.setImageResource(R.drawable.shoppingcarone);
        }


       /* id=intents.getStringExtra("id");
        if(id.equals("1")){
            initfragment3();
        }else{
            Log.i(TAG,"id为空");
        }*/

        homeimage.setImageResource(R.drawable.homepage_fill);
        initfragment1();

    }
    protected void getdata() {
        Intent in=getIntent();
        Bundle bun=in.getExtras();
        username=bun.getString("username");
        role=bun.getString("role");
        userid=bun.getString("userid");
        phone=bun.getString("phone");
        qq=bun.getString("qq");
        name=bun.getString("name");
        gender=bun.getString("gender");

    }


    private void initfragment1() {
         transaction=getSupportFragmentManager().beginTransaction();
        if(homefragement==null){
            homefragement=new Fragment1_activity();
            Bundle bund=new Bundle();
            bund.putString("username",username);
            bund.putString("role",role);
            bund.putString("userid",userid);
            bund.putString("phone",phone);
            bund.putString("qq",qq);
            bund.putString("name",name);
            bund.putString("gender",gender);
            homefragement.setArguments(bund);
            transaction.add(R.id.mVp,homefragement);
        }
       /* transaction.show(homefragement);   //显示要显示的fragment；*/
        hideAllFragment(transaction);   //影藏所有的fragment；
        transaction.show(homefragement);   //显示要显示的fragment；

        transaction.commit();
    }
    private void initfragment2() {
         transaction=getSupportFragmentManager().beginTransaction();
        if(shoppingfragment==null){
            shoppingfragment=new Fragment2_activity();
            Bundle bund=new Bundle();
            bund.putString("username",username);
            bund.putString("role",role);
            bund.putString("userid",userid);
            bund.putString("phone",phone);
            bund.putString("qq",qq);
            bund.putString("name",name);
            bund.putString("gender",gender);
            shoppingfragment.setArguments(bund);
            transaction.add(R.id.mVp,shoppingfragment);
        }
        /*transaction.show(shoppingfragment);   //显示要显示的fragment；*/
        hideAllFragment(transaction);   //影藏所有的fragment；
        transaction.show(shoppingfragment);   //显示要显示的fragment；

        transaction.commit();
    }
    private void initfragment3() {
         transaction=getSupportFragmentManager().beginTransaction();
        if(personalfragment==null){
            personalfragment=new Fragment3_activity();
            Bundle bund=new Bundle();
            bund.putString("username",username);
            bund.putString("role",role);
            bund.putString("userid",userid);
            bund.putString("phone",phone);
            bund.putString("qq",qq);
            bund.putString("name",name);
            bund.putString("gender",gender);
            personalfragment.setArguments(bund);
            transaction.add(R.id.mVp,personalfragment);
        }
       /* transaction.show(personalfragment);   //显示要显示的fragment；*/
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
       /* if(role.equals("2")){
            imageView.setImageResource(R.drawable.addition);
        }
        imageView.setImageResource(R.drawable.shoppingcarone);*/
        homeimage= (ImageView) findViewById(R.id.homeimage);
        personimage= (ImageView) findViewById(R.id.personimage);
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
          /*  fragments();*/
            imageview();
            homeimage.setImageResource(R.drawable.homepage_fill);
            initfragment1();
        }
        if(v==shoppingcar){
   //         fragments();
            imageview();
            if(role.equals("2")){
                imageView.setImageResource(R.drawable.addition_fill);
            }else if(role.equals("1")){
                imageView.setImageResource(R.drawable.publish_goods_fill);
            }

            initfragment2();
        }
        if(v==personal){
   //         fragments();
            imageview();
            personimage.setImageResource(R.drawable.people_fill);
            initfragment3();
        }
    }
  private void imageview(){
      homeimage.setImageResource(R.drawable.homeone);
      if(role.equals("2")){
          imageView.setImageResource(R.drawable.addition);
      }else if(role.equals("1")){
          imageView.setImageResource(R.drawable.shoppingcarone);
      }

      personimage.setImageResource(R.drawable.myone);
  }
    private void fragments(){
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.remove(personalfragment);
        transaction.remove(shoppingfragment);
        transaction.remove(homefragement);
        transaction.commit();
    }

}
