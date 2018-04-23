package com.example.administrator.vaf.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.activity.Shopdetail_activity;
import com.example.administrator.vaf.design.StringAndBitmap;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/25.
 */

public class Shophomeadapter extends RecyclerView.Adapter<Shophomeadapter.ViewHolder>{

    public Context contexts;
    ArrayList<Map<String, Object>> list;
    private String clientids,clientname,roles;
    public Shophomeadapter(Context context,ArrayList<Map<String, Object>> lists,String clientid,String clientname,String role){
        this.contexts=context;
        this.list=lists;
        this.clientids=clientid;
        this.clientname=clientname;
        roles=role;
    }
    public void updata(ArrayList<Map<String, Object>> list3){
        list=list3;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(contexts);
        View view=inflater.inflate(R.layout.shoplist,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Map map=list.get(position);
        holder.imageView.setImageBitmap(bitmap((String) map.get("image")));
        holder.shoptext.setText((String) map.get("shopname"));
        holder.pricetext.setText((String) map.get("price"));
        holder.businesstext.setText((String) map.get("username"));



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("image", (String) map.get("image"));
                bundle.putString("shopid",(String) map.get("shopid"));
                bundle.putString("shopname",(String) map.get("shopname"));
                bundle.putString("shopdescribe",(String) map.get("shopdescribe"));
                bundle.putString("username",(String) map.get("username"));
                bundle.putString("userid",(String) map.get("userid"));
                bundle.putString("price",(String) map.get("price"));
                bundle.putString("type",(String) map.get("type"));
                bundle.putString("produceplace",(String) map.get("produceplace"));
                bundle.putString("userids",clientids);
                bundle.putString("usernames",clientname);
                bundle.putString("role",roles);

                Intent intent=new Intent(contexts,Shopdetail_activity.class);
                intent.putExtras(bundle);
                contexts.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView imageView;
        public TextView shoptext;
        public TextView pricetext;
        public TextView businesstext;



        public ViewHolder(View view) {
            super(view);
            cardView= (CardView) view.findViewById(R.id.card_container);
            imageView = (ImageView) view.findViewById(R.id.images1);
            shoptext = (TextView) view.findViewById(R.id.shops);
            pricetext = (TextView) view.findViewById(R.id.prices1);
            businesstext = (TextView) view.findViewById(R.id.business1);
        }
    }
    private static Bitmap bitmap(String image){
        StringAndBitmap stringAndBitmap=new StringAndBitmap();
        Bitmap bitmaps=stringAndBitmap.stringToBitmap(image);
        return bitmaps;
    }

}

