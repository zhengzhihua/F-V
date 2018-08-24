package com.example.administrator.vaf.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.activity.Shopdetail_activity;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.StringAndBitmap;
import com.example.administrator.vaf.design.UUIDGenerator;
import com.example.administrator.vaf.model.Shopcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.R.attr.start;
import static com.alibaba.fastjson.JSON.toJSONString;


/**
 * Created by Administrator on 2018/3/31.
 */

public class Fragment2_adapter extends RecyclerView.Adapter<Fragment2_adapter.ViewHolder>{
    private static final String TAG = "Fragment2_adapter";
    Context mContext;
    private List<Shopcar> list;
    private String roles;
    private String where,tablenames;
    private TextView totals;

   private List lists1=new ArrayList();
    private String adress,userids;
 //   private CheckInterface checkinterface=null;
    private Button commits;
    private  float onetotal;
//    private ModifyCountInterface modifyCountInterface=null;
    public Fragment2_adapter(Context mContexts, List<Shopcar> lists, String role, TextView total, Button  commit,String adresss){
        mContext=mContexts;
        list=lists;
        roles=role;
        totals=total;
        commits=commit;
        adress=adresss;
    }
   /* public void setCheckInterface(CheckInterface checkInterface) {
        this.checkinterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }
*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment2_list,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        final Shopcar shopcar=list.get(position);
        userids=shopcar.getUserids();
        final ViewHolder holders= (ViewHolder) holder;
        holders.imageView.setImageBitmap(bitmap(shopcar.getImage()));
        holders.shopname.setText(shopcar.getShopname());
        holders.pricetext.setText(shopcar.getPrice());
        holders.companyname.setText(shopcar.getUsername());
        holders.shuliang.setText(shopcar.getNum()); ///------------------------------------------------------------------------------------------
        holders.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("image", shopcar.getImage());
                bundle.putString("shopname", shopcar.getShopname());
                bundle.putString("username", shopcar.getUsername());
                bundle.putString("shopid", shopcar.getShopid());
                bundle.putString("shopdescribe", shopcar.getShopdescribe());
                bundle.putString("price", shopcar.getPrice());
                bundle.putString("type", shopcar.getType());
                bundle.putString("produceplace", shopcar.getProduceplace());
                bundle.putString("userid", shopcar.getUserid());
                bundle.putString("usernames", shopcar.getUsernames());
                bundle.putString("userids", shopcar.getUserids());
                bundle.putString("role",roles);
                bundle.putString("primaryprice", shopcar.getPrimaryprice());
                Intent intent=new Intent(mContext,Shopdetail_activity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });

        commits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// 模仿总额调用接口------------------------------------------------====================================================================

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        //
                        //     AlertDialog adlg=builder.create();
                        builder.setIcon(R.drawable.adress);
                        builder.setTitle("请确认地址没有请先返回补充地址");
                        builder.setMessage(adress);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int j = 0; j < list.size(); j++) {
                                    Shopcar product = list.get(j);
                                    if (product.isChoosed()) {
                                        String totalmoney=String.valueOf(Float.parseFloat(product.getNum())*Float.parseFloat(product.getPrice()));
                                        String orderformid = UUIDGenerator.getUUID();
                                        String Words = "orderformid,username,shopname,shopid,userid,status,image,price,clientname,clientid,number,finishtime,totalmoney";
                                        String values = "'" + orderformid + "','" + product.getUsername() + "','" + product.getShopname() + "','" + product.getShopid() + "','" + product.getUserid() + "','已下单','" + product.getImage() + "','" + product.getPrice() + "','" + product.getUsernames() + "','" + product.getUserids() + "','1','无','"+totalmoney+"'";
                                        Httpmanager.insertdata1(mContext, product.getUserid(), userids, totalmoney, "orderform", Words, values, new HttpRequestHandler<String>() {
                                            @Override
                                            public void onSuccess(String data) {

                                                Toast.makeText(mContext, data, Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onSuccesss(ArrayList<Map<String, Object>> res) {

                                            }

                                            @Override
                                            public void onSuccess(String data, int totalPages, int currentPage) {

                                            }

                                            @Override
                                            public void onFailure(String error) {
                                                Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
                                            }
                                        });
                                       String wheres="shopcarid='"+product.getShopcarid()+"'";
                                       String tablenamess="shopcar";
                                        Httpmanager.delete(mContext,tablenamess,wheres, new HttpRequestHandler<String>() {
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
                                                Toast.makeText(mContext,error,Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }}
                            }

                        });

                        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        //    AlertDialog adlg=builder.create();
                        builder.show();




            }
        });
        holders.deleteimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //invoking delete interface

                if(roles.equals("2")){
                    tablenames="commodity_bank";
                     where="shopid='"+shopcar.getShopid()+"'";
                }else if(roles.equals("1")){
                    where="shopcarid='"+shopcar.getShopcarid()+"'";
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
       /* if(shopcar != null) {
            holders.select.setChecked(shopcar.isChoosed());

            holders.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shopcar.setChoosed(((CheckBox) v).isChecked());
                    holders.select.setChecked(((CheckBox) v).isChecked());
                    checkinterface.checkChild(position, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });*/
        holders.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holders.select.isChecked()) {
                    shopcar.setChoosed(true);
                    calculate();
                }else{
                    shopcar.setChoosed(false);
                    calculate();
                }
            }
        });


            holders.jianshuliang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holders.select.isChecked()) {
                        shopcar.setChoosed(true);
                        int nums = Integer.parseInt(shopcar.getNum());;
                        if (nums > 1) {
                            holders.shuliang.setText(String.valueOf(nums - 1));
                            shopcar.setNum(holders.shuliang.getText().toString());
                            int nums1 = Integer.parseInt(shopcar.getNum());
                            notifyDataSetChanged();
                            calculate();

                           /* float sigleprices = Float.parseFloat(shopcar.getPrice());
                            onetotal = nums1 * sigleprices;
                            totals.setText(String.valueOf(onetotal));*/

                        } else {
                            //   Toast.makeText(mContext,"数量已经为零了",Toast.LENGTH_LONG).show();
                            return;
                        }
                        }else{
                        shopcar.setChoosed(false);
                        return;
                    }


      //              modifyCountInterface.doIncrease(position, holders.shuliang, holders.select.isChecked());
                }
            });
            holders.jiashuliang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holders.select.isChecked()) {
                    //    a[position]=true;
                        shopcar.setChoosed(true);
                        int nums = Integer.parseInt(shopcar.getNum());
                        holders.shuliang.setText(String.valueOf(nums+1));
                        shopcar.setNum( holders.shuliang.getText().toString());

                        int nums1 = Integer.parseInt(shopcar.getNum());
                        float sigleprices = Float.parseFloat(shopcar.getPrice());
                        notifyDataSetChanged();
                        calculate();

                       /* onetotal = nums1 * sigleprices;

                        totals.setText(String.valueOf(onetotal));*/
                    }else{
                        shopcar.setChoosed(false);
                        return;
                    }
      //              modifyCountInterface.doDecrease(position, holders.shuliang, holders.select.isChecked());
                }
            });
    }


  //  }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;   //卡片布局：那个卡片
        public ImageView imageView;
        public TextView shopname;
        public  TextView shuliang;//商品数量
        public ImageView jianshuliang,jiashuliang;
        public TextView companyname;
        public TextView pricetext;
        public ImageView deleteimage;
        public CheckBox select;//选择按钮

        public ViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_container6);
            imageView= (ImageView) itemView.findViewById(R.id.images7);
            shopname= (TextView) itemView.findViewById(R.id.shops7);
            companyname= (TextView) itemView.findViewById(R.id.business7);
            pricetext= (TextView) itemView.findViewById(R.id.prices7);
            deleteimage= (ImageView) itemView.findViewById(R.id.deleteimage7);
            shuliang= (TextView) itemView.findViewById(R.id.number7);
            jianshuliang= (ImageView) itemView.findViewById(R.id.jianshuliang);
            jiashuliang= (ImageView) itemView.findViewById(R.id.jiashuliang);
            select= (CheckBox) itemView.findViewById(R.id.select_dialog_listview);
        }

    }
//   /* public interface CheckInterface {
//        *//**
//         * 组选框状态改变触发的事件
//         *
//         * @param groupPosition 组元素位置
//         * @param isChecked     组元素选中与否
//         *//*
////        public void checkGroup(int groupPosition, boolean isChecked);
//
//        *//**
//         * 子选框状态改变时触发的事件
//         *
//         * @param childPosition 子元素位置
//         * @param isChecked     子元素选中与否
//         *//*
//        public void checkChild(int childPosition, boolean isChecked);
//    }
//
//    *//**
//     * 改变数量的接口
//     *//*
//    public interface ModifyCountInterface {
//        *//**
//         * 增加操作
//         *
//         * @param childPosition 子元素位置
//         * @param showCountView 用于展示变化后数量的View
//         * @param isChecked     子元素选中与否
//         *//*
//        public void doIncrease(int childPosition, View showCountView, boolean isChecked);
//
//        *//**
//         * 删减操作
//         *
//         * @param childPosition 子元素位置
//         * @param showCountView 用于展示变化后数量的View
//         * @param isChecked     子元素选中与否
//         /*
//        public void doDecrease(int childPosition, View showCountView, boolean isChecked);
//    }*/
    private void calculate() {
        onetotal = 0;
        for (int j = 0; j < list.size(); j++) {
            Shopcar product = list.get(j);
            if (product.isChoosed()) {
                onetotal += Float.parseFloat(product.getPrice()) * Float.parseFloat(product.getNum());
            }
        }
        totals.setText("¥" + onetotal);

    }

    private static Bitmap bitmap(String image){
        StringAndBitmap stringAndBitmap=new StringAndBitmap();
        Bitmap bitmaps=stringAndBitmap.stringToBitmap(image);
        return bitmaps;
    }

}
