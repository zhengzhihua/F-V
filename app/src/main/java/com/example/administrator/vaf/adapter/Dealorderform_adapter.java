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
import com.example.administrator.vaf.activity.Criticism_detail;
import com.example.administrator.vaf.activity.Dealorderform_activity;
import com.example.administrator.vaf.activity.Main_activity;
import com.example.administrator.vaf.activity.Orderformdetail_activity;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.StringAndBitmap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Dealorderform_adapter extends RecyclerView.Adapter<Dealorderform_adapter.ViewHolder>{

    private String status,roles;
    ArrayList<Map<String,Object>> list;
    private Context mcontext;
    private String set,message;
    public Dealorderform_adapter(Context context, ArrayList<Map<String, Object>> lists,String role){
        mcontext=context;
        list=lists;
        roles=role;
     }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.activity_dealorderform,parent,false);
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
        if(roles.equals("2")){
            holder.chexiaobutton.setVisibility(View.INVISIBLE);
        }
        if(roles.equals("1")&&map.get("status").equals("已出货")){
            holder.changebutton.setText("收货");
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dates=simpleDateFormat.format(date);
            set="status='订单完成',number='1',finishtime='"+dates+"'";
            message="已收货";
            onclick(holder,map);

        }else if(roles.equals("1")&&map.get("status").equals("已下单")){
            holder.changebutton.setText("待发货");
            holder.changebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mcontext,"未发货",Toast.LENGTH_LONG).show();
                }
            });
        }
        else if(roles.equals("2")&&map.get("status").equals("已下单")){
            holder.changebutton.setText("出货");
            set="status='已出货'";
            message="已出货";
            onclick(holder,map);
        }else if(roles.equals("2")&&map.get("status").equals("已出货")){
            holder.changebutton.setText("待收货");
        }
        holder.chexiaobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( map.get("status").equals("待发货")) {

                String word="money";
                String where="userid='"+map.get("clientid")+"'";
                Httpmanager.selectdata1(mcontext, "money", word, where, new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {

                    }

                    @Override
                    public void onSuccesss(ArrayList<Map<String, Object>> res) {
                      if(res.size()>0){
                          String money=String.valueOf(Float.parseFloat((String) res.get(0).get("money"))+Float.parseFloat((String) map.get("totalmoney")));
                          String sets="money='"+money+"'";
                          String wheres="userid='"+map.get("clientid")+"'";
                          Httpmanager.updata(mcontext, "money", sets, wheres, new HttpRequestHandler<String>() {
                              @Override
                              public void onSuccess(String data) {
                                  if(data.equals("添加成功")){
                                      String where1="orderformid='"+map.get("orderformid")+"'";
                                      Httpmanager.delete(mcontext, "orderform", where1, new HttpRequestHandler<String>() {
                                          @Override
                                          public void onSuccess(String data) {
                                              if(data.equals("成功")){
                                                  Toast.makeText(mcontext,"已撤销",Toast.LENGTH_LONG).show();
                                              }
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
                    }

                    @Override
                    public void onSuccess(String data, int totalPages, int currentPage) {

                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
                }else{
                    Toast.makeText(mcontext,"已发货无法撤销",Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("username", (String) map.get("username"));
                bundle.putString("userid", (String) map.get("userid"));
                bundle.putString("shopname", (String) map.get("shopname"));
                bundle.putString("status", (String) map.get("status"));
                bundle.putString("clientname", (String) map.get("clientname"));
                bundle.putString("clientid", (String) map.get("clientid"));
                bundle.putString("adressdetail", (String) map.get("adressdetail"));
                bundle.putString("startdatetime", (String) map.get("startdatetime"));
                bundle.putString("finishtime", (String) map.get("finishtime"));
                bundle.putString("totalmoney", (String) map.get("totalmoney"));
                bundle.putString("price", (String) map.get("price"));
                Intent intent=new Intent(mcontext, Orderformdetail_activity.class);
                intent.putExtras(bundle);
                mcontext.startActivity(intent);

            }
        });


    }

    private void onclick(ViewHolder holder,final Map map){
        holder.changebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  where="orderformid='"+map.get("orderformid")+"'";
                Httpmanager.updata(mcontext, "orderform",set,where, new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(mcontext,message,Toast.LENGTH_LONG).show();
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
               /* Intent intent=new Intent(mcontext,Main_activity.class);
                intent.putExtra("id","1");
                mcontext.startActivity(intent);*/
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
        public Button changebutton;    //删除订单按钮
        private Button chexiaobutton;  //撤销订单按钮
        public ViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_container5);
            imageView= (ImageView) itemView.findViewById(R.id.images5);
            shoptext= (TextView) itemView.findViewById(R.id.shops5);
            pricetext= (TextView) itemView.findViewById(R.id.prices5);
            businesstext= (TextView) itemView.findViewById(R.id.business5);
            statustext= (TextView) itemView.findViewById(R.id.status5);
            chexiaobutton= (Button) itemView.findViewById(R.id.chexiao);
            changebutton= (Button) itemView.findViewById(R.id.changestatus5);
        }
    }
    private static Bitmap bitmap(String image){
        StringAndBitmap stringAndBitmap=new StringAndBitmap();
        Bitmap bitmaps=stringAndBitmap.stringToBitmap(image);
        return bitmaps;
    }
}
