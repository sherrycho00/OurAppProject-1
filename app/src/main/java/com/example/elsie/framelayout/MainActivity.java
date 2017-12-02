package com.example.elsie.framelayout;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elsie.framelayout.Chat.ChatActivity;
import com.example.elsie.framelayout.Chat.ChatFragment;
import com.example.elsie.framelayout.Rank.RankFragment;
import com.example.elsie.framelayout.Setting.SettingFragment;

import static com.example.elsie.framelayout.R.color.material_grey_50;

public class  MainActivity extends FragmentActivity implements View.OnClickListener{

//    底部4个导航的控件
    private LinearLayout mTabHome,mTabChat,mTabRank,mTabSetting;

//    底部4个导航控件中的图片按钮
    private ImageView mImagHome,mImagChat,mImagRank,mImagSetting;

//    底部4个导航控件中图标上的文字
    private TextView mTxvHome,mTxvChat,mTxvRank,mTxvSetting;

//    初始化4个Fragment
    private android.support.v4.app.Fragment Home;
    private ChatFragment Chat;
    private android.support.v4.app.Fragment Rank;
    private android.support.v4.app.Fragment Setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

        initEvents();

//        默认显示微信聊天界面
        setSelect(0);


    }




    //    初始化所有view
    private void initView() {
//      获得布局文件
        mTabChat = (LinearLayout)findViewById(R.id.tab_chat);
        mTabHome = (LinearLayout)findViewById(R.id.tab_home);
        mTabRank = (LinearLayout)findViewById(R.id.tab_rank);
        mTabSetting = (LinearLayout)findViewById(R.id.tab_setting);

//        获得图标的图片
        mImagHome = (ImageView) findViewById(R.id.home_img);
        mImagChat = (ImageView) findViewById(R.id.chat_img);
        mImagRank = (ImageView) findViewById(R.id.rank_img);
        mImagSetting= (ImageView) findViewById(R.id.setting_img);

//        获得图标文字
        mTxvHome = (TextView) findViewById(R.id.home_txv);
        mTxvChat = (TextView) findViewById(R.id.chat_txv);
        mTxvRank = (TextView) findViewById(R.id.rank_txv);
        mTxvSetting = (TextView) findViewById(R.id.setting_txv);

        
    }

//    点击事件
    private void initEvents() {

        mTabHome.setOnClickListener(this);
        mTabChat.setOnClickListener(this);
        mTabRank.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);



    }

//    对点击设立监听事件
    @Override
    public void onClick(View v) {
//        先将图片恢复之前状态
        resetImg();

        switch (v.getId()){
            case R.id.tab_home:
                setSelect(0);
                mTxvHome.setTextColor(0xff1B940A);
                mImagHome.setImageResource(R.drawable.home);
                break;

            case R.id.tab_chat:
                setSelect(1);
                mTxvChat.setTextColor(0xff1B940A);
                mImagChat.setImageResource(R.drawable.chat);
                break;

            case R.id.tab_rank:
                setSelect(2);
                mTxvRank.setTextColor(0xff1B940A);
                mImagRank.setImageResource(R.drawable.rank);
                break;

            case R.id.tab_setting:
                setSelect(3);
                mTxvSetting.setTextColor(0xff1B940A);
                mImagSetting.setImageResource(R.drawable.me);
                break;

            default:
                break;
        }
    }

//    设置显示内容的fragment
    private void setSelect(int i) {

        FragmentManager fm = getSupportFragmentManager();

//        创建一个事务
        FragmentTransaction transaction = fm.beginTransaction();

//        先将所有的Fragment隐藏，然后下面再开始处理具体要显示的Fragment
        hideFragment(transaction);

        switch (i) {
            case 0:
                if (Home == null) {
                    Home = new HomeFragment();
//                    将主页面的Fragment添加到Activity中
                    transaction.add(R.id.frameLayoutContent, Home);
                } else {
                    transaction.show(Home);
                }
                break;

            case 1:
                if (Chat == null){
                    Chat = new ChatFragment();
                    Intent intent=new Intent(MainActivity.this,ChatActivity.class);
                    startActivity(intent);
                    transaction.add(R.id.frameLayoutContent,Chat);

                }else{
                    transaction.show(Chat);
                }
                break;

            case 2:
                if (Rank == null) {
                    Rank = new RankFragment();
                    transaction.add(R.id.frameLayoutContent,Rank);
                }else{
                    transaction.show(Rank);
                }
                break;

            case 3:
                if (Setting == null) {
                    Setting = new SettingFragment();
                    transaction.add(R.id.frameLayoutContent,Setting);
                }else {
                    transaction.show(Setting);
                }
                break;

            default:
                break;
            }

            transaction.commit();
        }




    //    隐藏fragment
    private void hideFragment(android.support.v4.app.FragmentTransaction transaction) {

        if (Home != null) {
            transaction.hide(Home);
        }

        if (Chat != null) {
            transaction.hide(Chat);
        }

        if (Rank != null) {
            transaction.hide(Rank);
        }

        if (Setting != null) {
            transaction.hide(Setting);
        }
    }

    //点击后图标发生变化
    private void resetImg() {
        mImagHome.setImageResource(R.drawable.grayhome);
        mTxvHome.setTextColor(material_grey_50);

        mImagChat.setImageResource(R.drawable.graychat);
        mTxvChat.setTextColor(material_grey_50);

        mImagRank.setImageResource(R.drawable.grayrank);
        mTxvRank.setTextColor(material_grey_50);

        mImagSetting.setImageResource(R.drawable.grayme);
        mTxvSetting.setTextColor(material_grey_50);
    }
}
