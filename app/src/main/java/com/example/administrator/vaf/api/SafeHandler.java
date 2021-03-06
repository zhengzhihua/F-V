package com.example.administrator.vaf.api;


import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhengzhihua on 2018/3/1.
 */
public class SafeHandler {

    public static <E> void onSuccesss(HttpRequestHandler<E> handler, ArrayList<Map<String, Object>> data){
        try{
            handler.onSuccesss(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static <E> void onFailure (HttpRequestHandler<E> handler, String error){
        try{
            handler.onFailure(error);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static <E> void  onSuccess(HttpRequestHandler<E> handler, E data){
        try{
            handler.onSuccess(data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static <E> void  onSuccess(HttpRequestHandler<E> handler, E data, int totalPages, int currentPage){
        try{
            handler.onSuccess(data, totalPages, currentPage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
