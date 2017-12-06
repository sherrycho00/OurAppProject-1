package com.example.elsie.framelayout.Utils;

import android.content.Context;

import com.example.elsie.framelayout.R;
import com.example.elsie.framelayout.Rank.DataResult;
import com.example.elsie.framelayout.Rank.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elsie on 2017/12/2.
 * 数据解析
 */

public class Parser {

    /**
     * 获取raw数据，在此声明此数据为伪造数据
     * 请按照特殊情况，特殊分析
     * @param ctx
     * @return 商品集合
     */

    public static List<Product> getCateProductList(Context ctx){

          DataResult dataResult;
        List<Product> products=new ArrayList<Product>();;
        //获取raw，通过文件流的方式，再通过引用的Gson解析
//        通过该方法获得raw文件，取得对应
        InputStream is = ctx.getResources().openRawResource(R.raw.dish_json);
        try {
//            读取字节数
            byte [] buffer = new byte[is.available()] ;
//            从buffer中读取数据
            is.read(buffer);
//            将读取到的数据解析成中文
            String json = new String(buffer,"utf-8");
            //Gson解析
//            Gson提供了fromJson()方法来实现从Json相关对象到java实体的方法。
            dataResult = new Gson().fromJson(json, DataResult.class);
//            products=new ArrayList<Product>();
            //通过遍历，为了更好的操作，把产品类型和产品类型下的商品进行合并，
            // 此处是为了adapter更好的去操作
            //把每一个商品实体里都标注商品类型和类型编号
            for (int i=0;i<dataResult.getResults().size();i++){
                for (int j=0;j<dataResult.getResults().get(i).getFoodList().size();j++){
                    DataResult.ResultsBean.FoodListBean food=dataResult.getResults().get(i).getFoodList().get(j);
                    Product item=new Product();
                    item.setProductId(food.getID());
                    item.setType(dataResult.getResults().get(i).getTitle());
                    item.setFoodName(food.getFoodName());
                    item.setFoodPrice(food.getFoodPrice());
                    item.setSalesCount(food.getSalesCount());
                    item.setImageUrl(food.getImageUrl());
                    item.setSeleteId(i);
                    products.add(item);
                }
            }
            return products;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
//        return null;
    }
}
