package com.example.elsie.framelayout.Utils;

import android.content.Context;

import com.example.elsie.framelayout.R;
import com.example.elsie.framelayout.Rank.DishRank.DishDataResult;
import com.example.elsie.framelayout.Rank.DishRank.Product;
import com.example.elsie.framelayout.Rank.ServiceRank.WorkerInfo;
import com.google.gson.Gson;

import com.example.elsie.framelayout.Rank.ServiceRank.ServiceDataResult;
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

    //获得菜品json文件的信息
    public static List<Product> getCateProductList(Context ctx){

        DishDataResult dishDataResult;
        List<Product> products=new ArrayList<Product>();;
        //获取raw，通过文件流的方式，再通过引用的Gson解析
//        通过该方法获得raw文件，取得对应
//        InputStream is = ctx.getResources().openRawResource(R.raw.dish_json);
        InputStream is = ctx.getResources().openRawResource(R.raw.dish);
        try {
//            读取字节数
            byte [] buffer = new byte[is.available()] ;
//            从buffer中读取数据，读成json文件
            is.read(buffer);
//            将读取到的数据解析成中文
            String json = new String(buffer,"utf-8");

            //Gson解析
//            Gson提供了fromJson()方法来实现从Json相关对象到java实体的方法。
//          就是将一段json格式的字符串自动映射成一个对象
            dishDataResult = new Gson().fromJson(json, DishDataResult.class);
//            products=new ArrayList<Product>();
            //通过遍历，为了更好的操作，把产品类型和产品类型下的商品进行合并，
            // 此处是为了adapter更好的去操作
            //把每一个商品实体里都标注商品类型和类型编号
            for (int i = 0; i< dishDataResult.getResults().size(); i++){
                for (int j = 0; j< dishDataResult.getResults().get(i).getFoodList().size(); j++){
                    DishDataResult.ResultsBean.FoodListBean food= dishDataResult.getResults().get(i).getFoodList().get(j);
                    Product item=new Product();
                    item.setProductId(food.getID());
                    item.setType(dishDataResult.getResults().get(i).getTitle());
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

    //获得服务json文件的信息
    public static List<WorkerInfo> getWorkerInfoList(Context ctx){

       ServiceDataResult serviceDataResult;
        List<WorkerInfo> workerInfos=new ArrayList<WorkerInfo>();;
        //获取raw，通过文件流的方式，再通过引用的Gson解析
//        通过该方法获得raw文件，取得对应
//        InputStream is = ctx.getResources().openRawResource(R.raw.dish_json);
        InputStream is = ctx.getResources().openRawResource(R.raw.service);
        try {
//            读取字节数
            byte [] buffer = new byte[is.available()] ;
//            从buffer中读取数据，读成json文件
            is.read(buffer);
//            将读取到的数据解析成中文
            String json = new String(buffer,"utf-8");

            //Gson解析
//            Gson提供了fromJson()方法来实现从Json相关对象到java实体的方法。
//          就是将一段json格式的字符串自动映射成一个对象
            serviceDataResult = new Gson().fromJson(json, ServiceDataResult.class);
//            products=new ArrayList<Product>();
            //通过遍历，为了更好的操作，把产品类型和产品类型下的商品进行合并，
            // 此处是为了adapter更好的去操作
            //把每一个商品实体里都标注商品类型和类型编号
            for (int i = 0; i<serviceDataResult.getResults().size(); i++){
                for (int j = 0; j< serviceDataResult.getResults().get(i).getWorkerList().size(); j++){
                    ServiceDataResult.ResultsBean.WorkerListBean worker= serviceDataResult.getResults().get(i).getWorkerList().get(j);
                    WorkerInfo item=new WorkerInfo();
                    item.setScore(worker.getScore());
                    item.setWindow(worker.getWindow());
                    item.setWorkerID(worker.getWorkerID());
                    item.setWorkerImg(worker.getWorkerImg());
                    item.setWorkerName(worker.getWorkerName());
                    item.setType(serviceDataResult.getResults().get(i).getTitle());
                    workerInfos.add(item);
                }
            }
            return workerInfos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workerInfos;
//        return null;
    }
}
