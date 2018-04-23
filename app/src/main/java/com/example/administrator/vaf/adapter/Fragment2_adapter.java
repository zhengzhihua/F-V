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
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.activity.Shopdetail_activity;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.StringAndBitmap;

import java.util.ArrayList;
import java.util.Map;

import static android.R.attr.start;


/**
 * Created by Administrator on 2018/3/31.
 */

public class Fragment2_adapter extends RecyclerView.Adapter<Fragment2_adapter.ViewHolder>{
    private static final String TAG = "Fragment2_adapter";
    Context mContext;
    ArrayList<Map<String, Object>> list;
    private String roles;
    private String where,tablenames;
    public Fragment2_adapter(Context mContexts,ArrayList<Map<String, Object>> lists,String role){
        mContext=mContexts;
        list=lists;
        roles=role;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment2_list,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Map map=list.get(position);
        ViewHolder holders= (ViewHolder) holder;
        holders.imageView.setImageBitmap(bitmap((String) map.get("image")));
        holders.shopname.setText((String) map.get("shopname"));
        holders.pricetext.setText((String) map.get("price"));
        holders.companyname.setText((String) map.get("username"));
        holders.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("image", (String) map.get("image"));
                bundle.putString("shopname", (String) map.get("shopname"));
                bundle.putString("username", (String) map.get("username"));
                bundle.putString("shopid", (String) map.get("shopid"));
                bundle.putString("shopdescribe", (String) map.get("shopdescribe"));
                bundle.putString("price", (String) map.get("price"));
                bundle.putString("type", (String) map.get("type"));
                bundle.putString("produceplace", (String) map.get("produceplace"));
                bundle.putString("userid", (String) map.get("userid"));
                bundle.putString("usernames", (String) map.get("usernames"));
                bundle.putString("userids", (String) map.get("userids"));
                bundle.putString("role",roles);
                Intent intent=new Intent(mContext,Shopdetail_activity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        holders.deleteimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //invoking delete interface

                if(roles.equals("2")){
                    tablenames="orderform";
                     where="orderformid='"+map.get("orderformid")+"'";
                }else if(roles.equals("1")){
                    where="shopcarid='"+map.get("shopcarid")+"'";
                    tablenames="shopcar";
                }

                Httpmanager.delete(mContext,tablenames,where, new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {

                        Toast.makeText(mContext,"已删除",Toast.LENGTH_LONG).show();
                       /* new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(contexts,mes,Toast.LENGTH_LONG).show();
                            }
                        },1000);
*/

                    }

                    @Override
                    public void onSuccesss(ArrayList<Map<String, Object>> res) {

                    }

                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {

                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(mContext,error,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;   //卡片布局：那个卡片
        public ImageView imageView;
        public TextView shopname;
        public TextView companyname;
        public TextView pricetext;
        public ImageView deleteimage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_container6);
            imageView= (ImageView) itemView.findViewById(R.id.images6);
            shopname= (TextView) itemView.findViewById(R.id.shops6);
            companyname= (TextView) itemView.findViewById(R.id.business6);
            pricetext= (TextView) itemView.findViewById(R.id.prices6);
            deleteimage= (ImageView) itemView.findViewById(R.id.deleteimage6);

        }
    }

    private static Bitmap bitmap(String image){
        StringAndBitmap stringAndBitmap=new StringAndBitmap();
        Bitmap bitmaps=stringAndBitmap.stringToBitmap(image);
        return bitmaps;
    }
}
