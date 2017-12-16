package com.example.elsie.framelayout.Rank.DishRank;

import java.util.Comparator;

/**
 * Created by Elsie on 2017/12/5.
 * 用于通过比较菜品的推荐数决定了菜的排行
 */

public class DishComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        int flag = o2.getSalesCount() - o1.getSalesCount();
        if (flag > 0) {
            return 1;
        }
        if (flag < 0) {
            return -1;
        }
        return flag;
    }



}
