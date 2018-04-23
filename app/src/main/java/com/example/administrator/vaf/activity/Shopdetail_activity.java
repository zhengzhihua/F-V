package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.adapter.Criticism_adapter;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.StringAndBitmap;
import com.example.administrator.vaf.design.UUIDGenerator;

import java.util.ArrayList;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Created by Administrator on 2018/3/27.
 */

public class Shopdetail_activity extends AppCompatActivity{
    private static final String TAG="Shopdetail_activity";
    private ImageView shopimage;
    private TextView shopnametext,detailtext2,pricetext2,typetext2,produceplacetext,companytext2;
    private ImageView shopcarimage,payforimage;
    String images,shopname,username,shopid,shopdescribe,price,type,userid,produceplace,userids,usernames,role;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Map<String,Object>> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopdetail_activity);
        Intent in=getIntent();
        Bundle bun=in.getExtras();
        images=bun.getString("image");
        shopname=bun.getString("shopname");
        username=bun.getString("username");
        shopid=bun.getString("shopid");
        shopdescribe=bun.getString("shopdescribe");
        price=bun.getString("price");
        type=bun.getString("type");
        userid=bun.getString("userid");
        produceplace=bun.getString("produceplace");
        userids=bun.getString("userids");
        usernames=bun.getString("usernames");
        role=bun.getString("role");
        initview();


    }

    private void initdata() {
        shopimage.setImageBitmap(bitmap(images));
        shopnametext.setText(shopname);
        detailtext2.setText(shopdescribe);
        pricetext2.setText(price);
        typetext2.setText(type);
        produceplacetext.setText(produceplace);
        companytext2.setText(username);


    }



    private void initview() {
        shopimage= (ImageView) findViewById(R.id.imageview3);
        shopnametext= (TextView) findViewById(R.id.shopname2);
        detailtext2= (TextView) findViewById(R.id.shopdetail1);
        pricetext2= (TextView) findViewById(R.id.price2);
        typetext2= (TextView) findViewById(R.id.shoptype2);
        produceplacetext= (TextView) findViewById(R.id.productplace1);
        companytext2= (TextView) findViewById(R.id.company1);
        shopcarimage= (ImageView) findViewById(R.id.imageView2);
        payforimage= (ImageView) findViewById(R.id.imageview1);
        if(role.equals("2")){
            shopcarimage.setVisibility(View.INVISIBLE);
            payforimage.setVisibility(View.INVISIBLE);
        }
        getdata();
        recyclerView= (RecyclerView) findViewById(R.id.recycleview3);

        initdata();

        linearLayoutManager=new LinearLayoutManager(Shopdetail_activity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


     /*   shopimage.setOnClickListener(shopimagelistener);*/


        shopcarimage.setOnClickListener(shopcarimageliestener);

        payforimage.setOnClickListener(payforimagelistener);

    }
    private void getdata() {
        String where="shopid='"+shopid+"'";
        String word="criticismid,shopname,shopusername,shopuserid,storeuserid,criticismdetail";
        Httpmanager.selectdata1(Shopdetail_activity.this, "criticism", word, where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {

                if(!res.equals("")&&res!=null){

                    list=res;
                    Log.i(TAG,toJSONString(list));
                    Criticism_adapter criticism_adapter=new Criticism_adapter(Shopdetail_activity.this,list);
                    recyclerView.setAdapter(criticism_adapter);
                }else{
                Toast.makeText(Shopdetail_activity.this,"暂无评论",Toast.LENGTH_LONG).show();
            }}

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {

            }

            @Override
            public void onFailure(String error) {

            }
        });

    }



    /*  View.OnClickListener shopimagelistener=new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      };*/
    View.OnClickListener shopcarimageliestener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String shopcarid= UUIDGenerator.getUUID();
            String word="(shopcarid,shopid,shopname,shopdescribe,username,userid,price,type,produceplace,image,usernames,userids)";
            String value="('"+shopcarid+"','"+shopid+"','"+shopname+"','"+shopdescribe+"','"+username+"','"+userid+"','"+price+"','"+type+"','"+produceplace+"','"+images+"','"+usernames+"','"+userids+"')";
        Httpmanager.insertdata(Shopdetail_activity.this,"","","shopcar",word,value, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {
            Toast.makeText(Shopdetail_activity.this,"已加入购物车",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {

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
    View.OnClickListener payforimagelistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String orderformid=UUIDGenerator.getUUID();
            String Words="orderformid,username,shopname,shopid,userid,status,image,price,clientname,clientid,number";
            String values="'"+orderformid+"','"+username+"','"+shopname+"','"+shopid+"','"+userid+"','已下单','"+images+"','"+price+"','"+usernames+"','"+userids+"','1'";
    Httpmanager.insertdata1(Shopdetail_activity.this, userid, userids,price, "orderform",Words, values, new HttpRequestHandler<String>() {
        @Override
        public void onSuccess(String data) {
            Toast.makeText(Shopdetail_activity.this,"已购买",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onSuccesss(ArrayList<Map<String, Object>> res) {

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
//将String图片转bitmap对象
    private static Bitmap bitmap(String image) {
        StringAndBitmap stringAndBitmap = new StringAndBitmap();
        Bitmap bitmaps = stringAndBitmap.stringToBitmap(image);
        return bitmaps;
    }

}
