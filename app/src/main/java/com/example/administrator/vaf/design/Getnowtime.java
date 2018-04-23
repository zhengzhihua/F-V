package com.example.administrator.vaf.design;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/28.
 */

public class Getnowtime {
    public static String systemtime(){
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String   str   =   formatter.format(curDate);
        return str;
    }
}
