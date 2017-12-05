package com.example.elsie.framelayout.Rank;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.elsie.framelayout.R;
import com.example.elsie.framelayout.Utils.Parser;

import java.util.List;

/**
 * Created by Elsie on 2017/11/29.
 * 在这里实现菜品排行榜
 *
 */

public class DishFragment extends android.support.v4.app.Fragment  {

    private RecyclerView menu;
    private MenuAdapter  dishAdapter;//适配器
    private List<Product> mProductList;//数据源

    private LinearLayout mRankTop;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rank, container,false);
        initView(view);
//        获得菜品数据并建立适配器和数据的关系
        initData();
        mProductList = Parser.getCateProductList(getActivity());

        //        建立适配器和数据关系
        dishAdapter = new MenuAdapter(mProductList);
        menu = (RecyclerView) view.findViewById(R.id.menu_lv);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        menu.setLayoutManager(layoutManager);

        //        建立listView和适配器的关系
        menu.setAdapter(dishAdapter);

        return view;
    }


    //    初始化数据
    private void initData() {
        //        Parser是自己定义的类，mProductList是数据来的
        mProductList = Parser.getCateProductList(getActivity());

    }


    //    从id中获得控件
    private void initView(View view) {
        menu = (RecyclerView) view.findViewById(R.id.menu_lv);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        menu.setLayoutManager(layoutManager);

//        隐藏顶部布局
        mRankTop = (LinearLayout) view.findViewById(R.id.rank_top);
        mRankTop.setVisibility(View.GONE);
    }



}
