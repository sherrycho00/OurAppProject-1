package com.example.elsie.framelayout.Setting;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.elsie.framelayout.R;

/**
 * Created by Elsie on 2017/11/3.
 * 个人设置界面
 */

public class SettingFragment extends android.support.v4.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //final String edit_name;
        //user_name=(TextView)getActivity().findViewById(R.id.username);
        //user_name.setText(PersonalModel.name);
        Button setting = (Button) getActivity().findViewById(R.id.usr_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PersonalSettingActivity.class);
                startActivity(intent);
            }
        });
        Button feedback=(Button)getActivity().findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),FeedbackActivity.class);
                startActivity(intent);
            }
        });
        Button share=(Button)getActivity().findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(getActivity(),UpdateActivity.class);
                //startActivity(intent);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "推荐一款好用的app给你哦，快来试试吧～下载地址:http://www.fanfou.com");
                shareIntent.setType("text/plain");

                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, "分享到"));
            }
        });
        Button exit=(Button)getActivity().findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                ComponentName comp = new ComponentName("com.eg.android.AlipayGphone", "com.eg.android.AlipayGphone.AlipayLogin");
                intent.setComponent(comp);
                intent.putExtra("other", "true");
                intent.setAction("android.intent.action.VIEW");
                startActivity(intent);
                //intent.setClassName("com.eg.android.AlipayGphone","com.eg.android.AlipayGphone.AlipayLogin");
            }
        });
    }
}
