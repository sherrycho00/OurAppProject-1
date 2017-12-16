package com.example.elsie.framelayout.Rank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elsie.framelayout.R;
import com.example.elsie.framelayout.Rank.DishRank.DishFragment;
import com.example.elsie.framelayout.Rank.DishRank.Floor2data;
import com.example.elsie.framelayout.Rank.ServiceRank.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elsie on 2017/11/3.
 * 排行榜界面
 */

public class RankFragment extends android.support.v4.app.Fragment implements OnClickListener{

    private DrawerLayout            mDrawerLayout;
    private ListView                listView;
    private ImageView               mFloor;
    private boolean                 ImageFlag;
    private TextView                mWhichFloor;
    private Button                  mDishRank;
    private Button                  mServiceRank;

    private Fragment                dishFragment,serviceFragment;
    private FragmentManager         fm;
//    private FragmentTransaction     transaction;

    private String                  flag ;//用来标记点击的是哪个按钮

    static public Floor2data mFloorData;

    private int                     dishClickFlag = 1 , serviceClickFlag = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rank,container,false);

        flag = "dish";
        initView(view);

        mFloorData.setFloor(1);
        ImageFlag = true;
        mFloor.setOnClickListener(this);


        initData();

        mDishRank.setOnClickListener(this);
        mServiceRank.setOnClickListener(this);

        serviceFragment = new ServiceFragment();

        //讓界面初始化就有這個了
       FragmentTransaction     transaction0;
        dishFragment = new DishFragment();
        fm = getFragmentManager();
        transaction0 = fm.beginTransaction();
        transaction0.replace(R.id.dish_lycontent,dishFragment);
        transaction0.commit();

        return view;
    }

    private void show() {
        if (flag == "dish")
        {
            FragmentTransaction     transactionDish;
            transactionDish = fm.beginTransaction();
            transactionDish.replace(R.id.dish_lycontent,dishFragment);
            transactionDish.commit();}

        if (flag == "service")
        {
            FragmentTransaction     transactionDish;
            transactionDish = fm.beginTransaction();
            transactionDish.replace(R.id.dish_lycontent,serviceFragment);
            transactionDish.commit();}
    }

    //初始化放到listView中的数据
    private void initData() {
        final List<String> list = new ArrayList<String>();
        list.add("一楼");
        list.add("二楼");
        list.add("三楼");
        list.add("清真");
        list.add("红楼");

//        serviceFragment = new ServiceFragment();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:

                        Toast.makeText(getContext(),"1floor",Toast.LENGTH_LONG).show();
                        mFloor.setBackgroundResource(R.drawable.sort);
                        mWhichFloor.setText("一楼");
                        mWhichFloor.setVisibility(View.VISIBLE);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        ImageFlag = true;

//                        将数据按楼层分类
                        mFloorData.setFloor(1);


                        break;
                    case 1:
                        Toast.makeText(getContext(),"2floor",Toast.LENGTH_LONG).show();
                        mWhichFloor.setText("二楼");
                        mWhichFloor.setVisibility(View.VISIBLE);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        ImageFlag = true;
//                        将数据按楼层分类
                        mFloorData.setFloor(2);

                        break;
                    case 2:
                        Toast.makeText(getContext(),"3floor",Toast.LENGTH_LONG).show();
                        mFloor.setBackgroundResource(R.drawable.sort);
                        mWhichFloor.setText("三楼");
                        mWhichFloor.setVisibility(View.VISIBLE);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        ImageFlag = true;
//                        将数据按楼层分类
                        mFloorData.setFloor(3);

                        break;

                    case 3:
                        Toast.makeText(getContext(),"清真",Toast.LENGTH_LONG).show();
                        mFloor.setBackgroundResource(R.drawable.graychat);
                        mWhichFloor.setText("清真");
                        mWhichFloor.setVisibility(View.VISIBLE);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        ImageFlag = true;
//                        将数据按楼层分类
                        mFloorData.setFloor(4);

                        break;

                    case 4:
                        Toast.makeText(getContext(),"红楼",Toast.LENGTH_LONG).show();
                        mFloor.setBackgroundResource(R.drawable.graychat);
                        mWhichFloor.setText("红楼");
                        mWhichFloor.setVisibility(View.VISIBLE);
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        ImageFlag = true;
//                        将数据按楼层分类
                        mFloorData.setFloor(5);

                        break;
                    default:
                        break;
                }


                dishClickFlag = 0;
                serviceClickFlag = 0;
                dishFragment = new DishFragment();
                serviceFragment = new ServiceFragment();


                show();


            }
        });


//        打开侧滑
//        mDrawerLayout.openDrawer(Gravity.LEFT);


    }

    private void initView(View view) {
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        listView      = (ListView) view.findViewById(R.id.listView);
        mFloor        = (ImageView) view.findViewById(R.id.floor);
        mWhichFloor   = (TextView) view.findViewById(R.id.which_floor);
        mDishRank     = (Button) view.findViewById(R.id.dish_rank);
        mServiceRank  = (Button) view.findViewById(R.id.service_rank);


        mFloorData = new Floor2data();
    }

    //为点击事件添加监控
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击楼层导致图标变化
            case R.id.floor:
                if (ImageFlag){
                    mFloor.setBackgroundResource(R.drawable.sort_filling);
                    mWhichFloor.setVisibility(View.GONE);
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    ImageFlag = false;
                }else{
                    mFloor.setBackgroundResource(R.drawable.sort);
                    mWhichFloor.setVisibility(View.VISIBLE);
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    ImageFlag = true;
                }
                break;

//            //点击菜品
//            case R.id.dish_rank:
////                mDishRank.setBackgroundColor(Color.parseColor("#88888"));
////                mServiceRank.setBackgroundColor(Color.parseColor("#ffffff"));
//                //展示菜品
//                flag = "dish";
////                transaction.replace(R.id.dish_lycontent,dishFragment);
////                transaction.commit();
//
//                break;
//
//            //点击服务人员
//            case R.id.service_rank:
////                mServiceRank.setBackgroundColor();
////                mDishRank.setBackgroundColor(Color.parseColor("#ffffff"));
//
//                //展示服务人员评分
////                transaction.replace(R.id.dish_lycontent,serviceFragment);
////                transaction.commit();
//                flag = "service";
//                break;

            case R.id.service_rank:
                flag = "service";
                dishClickFlag = 1;
                if (serviceClickFlag == 1)
                    show();
                break;

            case R.id.dish_rank:
                flag = "dish";
                serviceClickFlag = 1;
                if (dishClickFlag == 1)
                    show();
                break;

            default:
                break;
        }
    }
}

