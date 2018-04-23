package com.example.administrator.vaf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.vaf.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/3.
 */

public class Criticism_adapter extends RecyclerView.Adapter<Criticism_adapter.ViewHolder>{

    private ArrayList<Map<String,Object>> lists;
    private  Context contexts;
    public Criticism_adapter(Context context, ArrayList<Map<String,Object>> list){
        lists=list;
        contexts=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
  //      View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_criticismlist,parent,false);
        ViewHolder viewHolder= new ViewHolder(LayoutInflater.from(contexts).inflate(R.layout.activity_criticismlist,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Map map=lists.get(position);
        holder.criticismuser.setText((String) map.get("shopusername"));
        holder.criticismtext.setText((String)map.get("criticismdetail"));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView criticismuser;
        public TextView criticismtext;
        public ViewHolder(View view){
            super(view);

            criticismuser= (TextView) view.findViewById(R.id.criticismuser);
            criticismtext= (TextView) view.findViewById(R.id.criticismtexts);
        }

    }
}
