package com.example.administrator.vaf.api;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.administrator.vaf.model.LoginResult;
import com.example.administrator.vaf.model.ReSult1;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhengzhihua on 2018/3/18.
 */

 public class Httpmanager {
    private static final String TAG = "Httpmanager";
    private static String uri="http://10.3.104.158:8081/excelservice/rest/service";


    /*
    ************登录
    */
    public static void loginWithUsername(final Context cxt, final String username, final String password, final HttpRequestHandler<String> handler
                                        ) {
        Map map=new HashMap();
        map.put("user",username);
        map.put("psw",password);
        String data= JSON.toJSONString(map);

        String ip_adress = uri+"/login";
        Log.i(TAG,"ip_adress="+ip_adress);//---------------------------------------------------------------------------------
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", data);


        client.setTimeout(20000);
        client.post(ip_adress, params, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(TAG,responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Log.i(TAG,responseString);//------------------------------------------------------------------
                if(statusCode==200){
                    ReSult1 loginResult= JsonUtil.parsingAuth(cxt,responseString);
                    if(loginResult !=null){
                        if(loginResult.getMessage().equals("登录成功")||loginResult.getSuccess().equals("true")){
                            SafeHandler.onSuccesss(handler, loginResult.getArraylist());
                        }else if(loginResult.getSuccess().equals("false")){
                            SafeHandler.onFailure(handler,loginResult.getMessage());

                        }
                    }
                }
            }
        });
    }

   /*
   *********注册
   * */
    public static void userregist(final Context cxt,final String tablename,final String username,final String phone,
                                 final String word,final String value,final HttpRequestHandler<String> handler){

        Map map=new HashMap();
        map.put("username",username);
        map.put("phone",phone);
        map.put("tablename",tablename);
        map.put("word",word);
        map.put("value",value);
        String data= JSON.toJSONString(map);
        String ip_dress=uri+"/regist";
        Log.i(TAG,ip_dress);//-------------------------------------------------------------------------------
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("data",data);
        client.post(ip_dress,params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG,responseString);//------------------------------------------------------------------
                if(statusCode==200){
                    LoginResult loginResult= JsonUtil.parsingAuthStr(cxt,responseString);
                    if(loginResult!=null&& ! loginResult.equals("")){
                        if(loginResult.getSuccess().equals("true")){
                            SafeHandler.onSuccess(handler,loginResult.getMessage());

                        }else if(loginResult.getSuccess().equals("false")){
                            SafeHandler.onFailure(handler,loginResult.getMessage());

                        }

                    }

                }
            }
        });


    }


    /* public final static String  url="http://10.3.104.158:18080/zzh-project/ws/CommonService";
    public final static String namespace="http://CommonService.com/";
    public static String action ="" ;

    public String getlogin(String user, String psw){
        String obj="";
        String methodName = "login";//要调用的方法名称
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        SoapObject soapReq = new SoapObject(namespace, methodName);
		soapReq.addProperty("user",user);
		soapReq.addProperty("psw", psw);

		soapEnvelope.bodyOut = soapReq;
        soapEnvelope.dotNet = false;
		soapEnvelope.setOutputSoapObject(soapReq);
		HttpTransportSE transport = new HttpTransportSE(url);
        try {
            transport.call("urn:action", soapEnvelope);
             obj = soapEnvelope.getResponse().toString();
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
// 获取返回的数据
//        SoapObject object = (SoapObject) soapEnvelope.bodyIn;
//        // 获取返回的结果
//       String result = object.getProperty(0).toString();
//        Log.d("debug",result);
//        return result;

        return obj;

    }
    public String getselect(String tablename, String word, String where) {
        String obj="";
        String methodName = "select";//要调用的方法名称
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        SoapObject soapReq = new SoapObject(namespace, methodName);
        soapReq.addProperty("tablename",tablename);
        soapReq.addProperty("word", word);
        soapReq.addProperty("where", where);

        soapEnvelope.bodyOut = soapReq;
      //  soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE transport = new HttpTransportSE(url);
        try {
            transport.call(null,soapEnvelope);
             obj = soapEnvelope.getResponse().toString();
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return obj;

    }

    public String getupdata(String tablename, String set, String where) throws Exception{

        String methodName = "updata";//要调用的方法名称
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        SoapObject soapReq = new SoapObject(namespace, methodName);
        soapReq.addProperty("tablename",tablename);
        soapReq.addProperty("set", set);
        soapReq.addProperty("where", where);

        soapEnvelope.bodyOut = soapReq;
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE transport = new HttpTransportSE(url);
        transport.call(action, soapEnvelope);

        String obj = soapEnvelope.getResponse().toString();
        return obj;

    }

    public String getinsert(String tablename, String word, String value) throws Exception{

        String methodName = "insert";//要调用的方法名称
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        SoapObject soapReq = new SoapObject(namespace, methodName);
        soapReq.addProperty("tablename",tablename);
        soapReq.addProperty("word", word);
        soapReq.addProperty("value", value);

        soapEnvelope.bodyOut = soapReq;
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE transport = new HttpTransportSE(url);
        transport.call(action, soapEnvelope);

        String obj = soapEnvelope.getResponse().toString();
        return obj;

    }
    public String getdelete(String tablename, String where) throws Exception{

        String methodName = "insert";//要调用的方法名称
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        SoapObject soapReq = new SoapObject(namespace, methodName);
        soapReq.addProperty("tablename",tablename);
        soapReq.addProperty("where", where);


        soapEnvelope.bodyOut = soapReq;
        soapEnvelope.setOutputSoapObject(soapReq);
        HttpTransportSE transport = new HttpTransportSE(url);
        transport.call(action, soapEnvelope);

        String obj = soapEnvelope.getResponse().toString();
        return obj;

    }
*/



}
