package com.example.administrator.vaf.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 */

public class Shopcars {
    public List<Shopcar> shopchange(List<Map<String,Object>> list){
        List lists=new ArrayList();
        for(int i=0;i<list.size();i++){
            Map map=list.get(i);
            Shopcar shopcar=new Shopcar();
            shopcar.setImage((String) map.get("image"));
            shopcar.setPrice((String) map.get("price"));
            shopcar.setProduceplace((String) map.get("produceplace"));
            shopcar.setShopdescribe((String) map.get("shopdescribe"));
            shopcar.setShopid((String) map.get("shopid"));
            shopcar.setShopname((String) map.get("shopname"));
            shopcar.setShopcarid((String) map.get("shopcarid"));
            shopcar.setType((String) map.get("type"));
            shopcar.setUserid((String) map.get("userid"));
            shopcar.setUserids((String) map.get("userids"));
            shopcar.setUsername((String) map.get("username"));
            shopcar.setUsernames((String) map.get("usernames"));
            shopcar.setNum((String) map.get("num"));
            shopcar.setPrimaryprice((String) map.get("primaryprice"));
            lists.add(shopcar);
        }
       return  lists;
    }
}
