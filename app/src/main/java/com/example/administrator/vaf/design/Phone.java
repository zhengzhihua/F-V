package com.example.administrator.vaf.design;

/**
 * Created by admin on 2018/3/4 0004.
 */

public class Phone {
    public static boolean isValidMobiNumber(String paramString) {
        String regex = "^1\\d{10}$";
        if (paramString.matches(regex)) {
            return true;
        }
        return false;
    }
}
