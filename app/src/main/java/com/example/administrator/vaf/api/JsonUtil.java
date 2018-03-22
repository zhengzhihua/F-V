package com.example.administrator.vaf.api;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.administrator.vaf.model.LoginResult;
import com.example.administrator.vaf.model.ReSult1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/3/20.
 */

public class JsonUtil {
    private static final String TAG = "JsonUtils";

    public static LoginResult parsingAuthStr(Context con,String data){
        LoginResult result=new LoginResult();
        try {
            JSONObject json = new JSONObject(data);
            String message=json.getString("message");
            Object object=json.getString("object");
            String success=json.getString("success");
            result.setMessage(message);
            result.setObject(object);
            result.setSuccess(success);
            return result;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ReSult1 parsingAuth(Context con, String data){

        try {
            JSONObject json = new JSONObject(data);
            ReSult1 result= JSON.parseObject(data,ReSult1.class);

            return result;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
