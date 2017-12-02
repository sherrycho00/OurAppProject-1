package com.example.elsie.framelayout.Rank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elsie on 2017/11/3.
 * 排行榜界面
 */

public class RankFragment extends android.support.v4.app.Fragment{

    private DrawerLayout            mDrawerLayout;
    private ListView                listView;
    private ImageView               mFloor;
    private boolean                 ImageFlag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rank,container,false);

        initView(view);

        ImageFlag = true;
        mFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ImageFlag){
                    mFloor.setBackgroundResource(R.drawable.chat);
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    ImageFlag = false;
                }else{
                    mFloor.setBackgroundResource(R.drawable.graychat);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    ImageFlag = true;
                }
            }
        });


        initData();
        return view;
    }

    private void initData() {
        final List<String> list = new ArrayList<String>();
        list.add("一楼");
        list.add("二楼");
        list.add("三楼");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getContext(),"1floor",Toast.LENGTH_LONG).show();
                        Fragment dishFragment = new DishFragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.add(R.id.dish_lycontent,dishFragment);
                        transaction.commit();
                        mFloor.setBackgroundResource(R.drawable.graychat);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case 1:
                        Toast.makeText(getContext(),"2floor",Toast.LENGTH_LONG).show();
                        mFloor.setBackgroundResource(R.drawable.graychat);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case 2:
                        Toast.makeText(getContext(),"3floor",Toast.LENGTH_LONG).show();
                        mFloor.setBackgroundResource(R.drawable.graychat);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    default:
                        break;
                }

            }
        });

//        打开侧滑
//        mDrawerLayout.openDrawer(Gravity.LEFT);


    }

    private void initView(View view) {
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        listView      = (ListView) view.findViewById(R.id.listView);
        mFloor        = (ImageView) view.findViewById(R.id.floor);
    }
}

