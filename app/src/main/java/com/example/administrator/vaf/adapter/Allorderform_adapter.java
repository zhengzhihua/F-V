package com.example.administrator.vaf.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
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
import com.example.administrator.vaf.activity.Main_activity;
import com.example.administrator.vaf.activity.Perfectuser_activity;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.StringAndBitmap;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Allorderform_adapter extends RecyclerView.Adapter<Allorderform_adapter.ViewHolder> {

    public Context contexts;
    ArrayList<Map<String, Object>> list;
    private String roles,mes;
    public Allorderform_adapter(Context context,ArrayList<Map<String, Object>> lists){
        this.contexts=context;
        this.list=lists;


    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(contexts);
        View view=layoutInflater.inflate(R.layout.activity_allorderform,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Map map=list.get(position);

        holder.imageView.setImageBitmap(bitmap((String) map.get("image")));
        holder.shoptext.setText((String) map.get("shopname"));
        holder.pricetext.setText((String) map.get("price"));
        holder.businesstext.setText((String) map.get("username"));   //=====================================================================================
        holder.deletebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String where="orderformid='"+map.get("orderformid")+"'";
                Httpmanager.delete(contexts, "orderform",where, new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {
                        mes=data;
                        Toast.makeText(contexts,mes,Toast.LENGTH_LONG).show();
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
                        Toast.makeText(contexts,error,Toast.LENGTH_LONG).show();
                    }
                });
               /* Intent intent=new Intent(contexts,Main_activity.class);
                intent.putExtra("id","1");
                contexts.startActivity(intent);*/
            }
        });
       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
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
        public Button deletebutton3;    //删除订单按钮


        public ViewHolder(View view) {
            super(view);
            cardView= (CardView) view.findViewById(R.id.card_container3);
            imageView= (ImageView) view.findViewById(R.id.images3);
            shoptext= (TextView) view.findViewById(R.id.shops3);
            pricetext= (TextView) view.findViewById(R.id.prices3);
            businesstext= (TextView) view.findViewById(R.id.business3);
            deletebutton3= (Button) view.findViewById(R.id.deletebutton3);




        }
    }
    private static Bitmap bitmap(String image){
        StringAndBitmap stringAndBitmap=new StringAndBitmap();
        Bitmap bitmaps=stringAndBitmap.stringToBitmap(image);
        return bitmaps;
    }
}
