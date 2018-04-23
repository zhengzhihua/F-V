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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.activity.Beforecriticism_activity;
import com.example.administrator.vaf.activity.Criticism_detail;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.StringAndBitmap;
import com.example.administrator.vaf.design.UUIDGenerator;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Beforecriticism_adapter extends RecyclerView.Adapter<Beforecriticism_adapter.ViewHolder>{
    private static final String TAG="Beforecriticism_adapter";
    public Context contexts;
    private String roles;
    ArrayList<Map<String, Object>> list;

    public Beforecriticism_adapter(Context context,ArrayList<Map<String, Object>> lists,String role){
        contexts=context;
        list=lists;
        roles=role;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.activity_beforecriticism,parent,false);
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
        holder.statustext.setText((String) map.get("status"));
        holder.ignorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String criticismid= UUIDGenerator.getUUID();
                String word="(criticismid,shopid,shopname,shopusername,shopuserid,storeuserid,criticismdetail)";
                String value="('"+criticismid+"','"+map.get("shopid")+"','"+map.get("shopname")+"','"+map.get("clientname")+"','"+map.get("clientid")+"','"+map.get("userid")+"','该用户未评论')";
                Httpmanager.insertdata(contexts, "", "", "criticism",word ,value,new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {

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
                String sets="number='2'";
                String wheres="orderformid='"+map.get("orderformid")+"'";
                Httpmanager.updata(contexts, "orderform", sets, wheres, new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(contexts,data,Toast.LENGTH_LONG).show();
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
        });
        holder.criticismbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(contexts, Criticism_detail.class);
                Bundle bundle=new Bundle();
                bundle.putString("orderformid", (String) map.get("orderformid"));
                bundle.putString("shopid", (String) map.get("shopid"));
                bundle.putString("shopname",(String) map.get("shopname"));
                bundle.putString("shopusername",(String) map.get("clientname"));
                bundle.putString("role",roles);
                bundle.putString("shopuserid",(String) map.get("clientid"));
                bundle.putString("storeuserid",(String) map.get("userid"));
                intent.putExtras(bundle);
                contexts.startActivity(intent);

               /* String word="(criticismid,shopid,shopname,shopusername,shopuserid,storeuserid,criticismdetail)";
                Httpmanager.insertdata(contexts,"","","criticism", "", "", new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {

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
                });*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;   //卡片布局：那个卡片
        public ImageView imageView;   //商品图片
        public TextView shoptext;     //商品名
        public TextView pricetext;     //商品价格
        public TextView businesstext;   //商家
        public TextView statustext;    //订单状态
        public Button ignorebutton;    //忽略评价按钮
        public Button criticismbutton;    //评价按钮
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.images4);
            shoptext= (TextView) itemView.findViewById(R.id.shops4);
            pricetext= (TextView) itemView.findViewById(R.id.prices4);
            businesstext= (TextView) itemView.findViewById(R.id.business4);
            statustext= (TextView) itemView.findViewById(R.id.status4);
            ignorebutton= (Button) itemView.findViewById(R.id.ignorebutton4);
            criticismbutton= (Button) itemView.findViewById(R.id.critivismbutton4);

        }
    }
    private static Bitmap bitmap(String image){
        StringAndBitmap stringAndBitmap=new StringAndBitmap();
        Bitmap bitmaps=stringAndBitmap.stringToBitmap(image);
        return bitmaps;
    }
}
