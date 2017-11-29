package com.example.elsie.framelayout;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Elsie on 2017/11/3.
 * 首页界面
 */

public class HomeFragment extends android.support.v4.app.Fragment{

//    用于引入我们的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home,container,false);
    }
}
