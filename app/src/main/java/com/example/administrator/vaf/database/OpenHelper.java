package com.example.administrator.vaf.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/12/19.
 */

public class OpenHelper extends SQLiteOpenHelper {

    //建表语句
    public static final String CREATE_USER = "CREATE TABLE users("
            + "userid text primary key NOT NULL, "
            + "username text, "
            + "userpwd text,"
            +"phone text ,"
            + "role text)";
    public static final String CREATE_SHOPVEHICLE="CREATE TABLE shopvehicle("
            +"shopvehicleid text primary key NOT NULL,"
            +"goodsid text,"
            +"goodsname text,"
            +"goodsnum text,"
            +"sellerid text,"
            +"sellername text,"
            +"createdate text,"
            +"price text,"
            +"state text)";
   public static final  String CREATE_GOODSBANK="CREATE TABLE goodsbank("
           +"goodsbankid text primary key NOT NULL,"
           +"goodsid text,"
           +"goodsname text,"
           +"beforeprice text,"
           +"nowprice text,"
           +"sellerid text,"
           +"sellername text,"
           +"image text)";



    public OpenHelper(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_USER);//创建用户表
        db.execSQL(CREATE_SHOPVEHICLE);//创建购物车表
        db.execSQL(CREATE_GOODSBANK);//创建商品库表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}