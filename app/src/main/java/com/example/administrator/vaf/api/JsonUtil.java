package com.example.administrator.vaf.api;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.administrator.vaf.model.LoginResult;
import com.example.administrator.vaf.model.ReSult1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhengzhihua on 2018/3/1.
 */

public class JsonUtil {
    private static final String TAG = "JsonUtils";

    public static LoginResult parsingAuthStr(Context con,String data){
        LoginResult result=new LoginResult();
        try {
            JSONObject json = new JSONObject(data);
            String message=json.getString("message");
           /* Object object=json.getString("object");*/
            String success=json.getString("success");
            result.setMessage(message);
           /* result.setObject(object);*/
            result.setSuccess(success);
            return result;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ReSult1 parsingAuth(Context con, String data){
        ReSult1 result=new ReSult1();
        try {
            JSONObject json = new JSONObject(data);
            result= JSON.parseObject(data,ReSult1.class);
            Log.i(TAG, String.valueOf(result.getArraylist()));
           /* result.setArraylist((ArrayList<Map<String, Object>>) json.get("arraylist"));*/
            return result;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static LoginResult parsingAuth1(Context con, String data){
        LoginResult result=new LoginResult();
        try {
            JSONObject json = new JSONObject(data);
            String message=json.getString("message");
            Object object=json.getString("arraylist");
            String success=json.getString("success");
            result.setMessage(message);
            result.setObject(object);
            result.setSuccess(success);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
