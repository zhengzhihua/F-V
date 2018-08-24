package com.example.administrator.vaf.model;

/**
 * Created by Administrator on 2018/5/9.
 */

public class Shopcar extends BaseInfo{
    private String shopcarid;
    private String shopid;
    private String shopname;
    private String shopdescribe;
    private String username;
    private String userid;
    private String price;
    private String type;
    private String produceplace;
    private String image;
    private String usernames;
    private String userids;
    private String num;
    private String primaryprice;


    public void setUserids(String userids) {
        this.userids = userids;
    }

    public String getUserids() {
        return userids;
    }

    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public String getShopcarid() {
        return shopcarid;
    }

    public void setShopcarid(String shopcarid) {
        this.shopcarid = shopcarid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopdescribe() {
        return shopdescribe;
    }

    public void setShopdescribe(String shopdescribe) {
        this.shopdescribe = shopdescribe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduceplace() {
        return produceplace;
    }

    public void setProduceplace(String produceplace) {
        this.produceplace = produceplace;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrimaryprice() {
        return primaryprice;
    }

    public void setPrimaryprice(String primaryprice) {
        this.primaryprice = primaryprice;
    }
}
