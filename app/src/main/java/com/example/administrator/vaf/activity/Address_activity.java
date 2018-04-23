package com.example.administrator.vaf.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.vaf.R;
import com.example.administrator.vaf.api.HttpRequestHandler;
import com.example.administrator.vaf.api.Httpmanager;
import com.example.administrator.vaf.design.UUIDGenerator;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by admin on 2018/3/14 0014.
 */

public class Address_activity extends AppCompatActivity{
    private static final String TAG = "Address_activity";
    private Button addbutton2;
    private EditText adressinput1;
    String userid,addressinputword,adressid,word,value;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Intent in=getIntent();
        Bundle bu=in.getExtras();
        userid=bu.getString("userid");

        initview();

    }

    private void initview() {
        addbutton2= (Button) findViewById(R.id.addbutton2);
        adressinput1= (EditText) findViewById(R.id.adressinput1);
        getdata();

        addbutton2.setOnClickListener(addbuttonlistener);

    }
    View.OnClickListener addbuttonlistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addressinputword=adressinput1.getText().toString().trim();
            if(!addressinputword.equals("")&&addressinputword!=null){
                adressid= UUIDGenerator.getUUID();
                word="(adressid,adress,userid)";
                value="('"+adressid+"','"+addressinputword+"','"+userid+"')";
                Httpmanager.insertdata(Address_activity.this,"" ,"","adress", word, value, new HttpRequestHandler<String>() {
                    @Override
                    public void onSuccess(String data) {

                                  Toast.makeText(Address_activity.this,"添加成功",Toast.LENGTH_LONG).show();




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
    };
    private void getdata(){
        String where="userid='"+userid+"'";
        String word="adress";
        Httpmanager.selectdata1(Address_activity.this, "adress", word, where, new HttpRequestHandler<String>() {
            @Override
            public void onSuccess(String data) {

            }


            @Override
            public void onSuccesss(ArrayList<Map<String, Object>> res) {

                if (!res.equals("") && res != null) {
                    adressinput1.setText((String) res.get(0).get("adress"));
                }
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
