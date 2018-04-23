package com.example.administrator.vaf.utils;


/**
 * 登录帐号管理Created by yw on 2015/5/5.
 */
public class AccountUtils {

    public static final int REQUEST_LOGIN = 0;

    private static final String key_login_member = "logined@member";
    private static final String key_fav_nodes = "logined@fav_nodes";

    /**
     * 记录是否记住密码
     *
     * @param cxt
     * @param isChecked *
     */
/*

    public static void setChecked(Context cxt, boolean isChecked) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putBoolean(cxt.getString(R.string.logined_member_ischeck), isChecked).commit();

    }
    */
/**
     * 读取记住状态*
     *//*

    public static boolean getIsChecked(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getBoolean(cxt.getString(R.string.logined_member_ischeck), false);
    }
    */
/**
     * 记录用户名与密码
     *
     * @param cxt
     * @param userName
     * @param password
     *//*


    public static void setUserNameAndPassWord(Context cxt, String userName, String password) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_username), userName).putString(cxt.getString(R.string.logined_member_password), password).commit();
    }
    */
/**
     * 设置用户名
     *//*

    public static String getUserName(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_username), "");
    }
    */
/**
     * 记住的用户名
     *//*

    public static void setUserName(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_username), userName).commit();
    }
    */
/**
     * 记住的密码
     *//*

    public static String getUserPassword(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString("user"), "");
    }


    */
/**
     * 记录用户名
     *
     * @param cxt
     * @param userName
     *//*


    public static void setUserid(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_usernid), userName).commit();
    }
    public static String getUserid(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_usernid), "");
    }

*/



/*

    */
/**
     * 设置服务器IP地址*
     *//*

    public static void setIpAddress(Context cxt, String ip) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.ip_address), ip).commit();

    }

    */
/**
     * 获取服务器IP地址*
     *//*

    public static String getIpAddress(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.ip_address), "");
    }

*/
/*
    *//**
     * 记录登录返回信息
     * @param cxt
     * @param insertSite
     * @param insertOrg
     * @param personId
     *
     *//*
    public static void setLoginDetails(Context cxt,String insertOrg,String insertSite,String description,String DISPLAYNAME,String myapps,String personId){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit()
                .putString(cxt.getString(R.string.login_insertOrg), insertOrg)
                .putString(cxt.getString(R.string.login_insertSite), insertSite)
                .putString(cxt.getString(R.string.login_personId), personId)
                .putString(cxt.getString(R.string.login_userName), description)
                .putString(cxt.getString(R.string.login_displayName), DISPLAYNAME)
                .putString(cxt.getString(R.string.login_UserName),myapps)
                .commit();
    }

    *//**获取myapps**//*
    public static String getmyapps(Context cxt){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.login_UserName), "");
    }
    *//**获取中文名称**//*
    public static String getdisplayName(Context cxt){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.login_displayName), "");
    }
    *//**获取用户登录名**//*
    public static String getloginUserName(Context cxt){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.login_UserName), "");
    }*/
}