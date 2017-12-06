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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.elsie.framelayout.Rank.RankFragment.mFloorData;

/**
 * Created by Elsie on 2017/11/29.
 * 在这里实现菜品排行榜
 *
 */

public class DishFragment extends android.support.v4.app.Fragment  {

    private RecyclerView menu;
    private MenuAdapter  dishAdapter;//适配器
    private List<Product> mProductList;
    private List mDishList;
    private List<Product> m1fDish;//数据源

    private LinearLayout mRankTop;
//    从点击事项获取楼层
//    private Floor2data mFloorData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rank, container,false);
        initView(view);
//        获得菜品数据并建立适配器和数据的关系
        initData();
        mProductList = Parser.getCateProductList(getActivity());
        m1fDish = new ArrayList<>();

//        将数据源分成一楼、二楼、……
        int temp = mFloorData.getFloor();
       SortByFloor(mFloorData.getFloor());

       //        建立适配器和数据关系
//        将数据排序
        Collections.sort(mDishList,new DishComparator());
        dishAdapter = new MenuAdapter(mDishList);
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

//    根据点击楼层将菜品分类
    private void SortByFloor(int floor) {
        Product dish;

        for (int i=0;i<mProductList.size();i++) {
            dish = mProductList.get(i);
            switch (floor) {
                case 1:
                    if (dish.getType().equals("一楼")) {
                        m1fDish.add(dish);
                    } else {
                        break;
                    }
                    break;

                case 2:
                    if (dish.getType().equals("二楼")) {
                        m1fDish.add(dish);
                    } else {
                        break;
                    }
                    break;

                case 3:
                    if (dish.getType().equals("三楼")) {
                        m1fDish.add(dish);
                    } else {
                        break;
                    }
                    break;

                case 4:
                    if (dish.getType().equals("清真")) {
                        m1fDish.add(dish);
                    } else {
                        break;
                    }
                    break;

                case 5:
                    if (dish.getType().equals("红楼")) {
                        m1fDish.add(dish);
                    } else {
                        break;
                    }
                    break;

                default:
                    break;
            }

        }

        mDishList = m1fDish;
    }




}
