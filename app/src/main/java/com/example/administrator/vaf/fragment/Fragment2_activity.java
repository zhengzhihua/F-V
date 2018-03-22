package com.example.administrator.vaf.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.vaf.R;

/**
 * Created by Administrator on 2017/12/27.
 */

public class Fragment2_activity extends Fragment {

    private Button addbutton,deletebutton;
    private String username;
    private String role;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bun=getArguments();
        username=bun.getString("username");
        role=bun.getString("role");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2_activity,container,false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        addbutton= (Button) view.findViewById(R.id.addbutton);
        deletebutton= (Button) view.findViewById(R.id.deletebutton);
        if(role.equals("商家")&& role=="商家"){
            deletebutton.setVisibility(View.GONE);
        }
        if(role.equals("用户")&& role=="用户"){
            addbutton.setVisibility(View.GONE);

        }

    }
}
