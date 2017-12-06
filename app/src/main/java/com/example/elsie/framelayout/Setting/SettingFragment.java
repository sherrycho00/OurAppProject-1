package com.example.elsie.framelayout.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

/**
 * Created by Elsie on 2017/11/3.
 * 个人设置界面
 */

public class SettingFragment extends android.support.v4.app.Fragment {
    public static ImageView mheader_img;
    public static TextView mnick_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mheader_img=(ImageView)getActivity().findViewById(R.id.image_user);
        mnick_name=(TextView)getActivity().findViewById(R.id.username);
        //header_img.setImageBitmap(mphoto);
        //final String edit_name;
        //user_name=(TextView)getActivity().findViewById(R.id.username);
        //user_name.setText(PersonalModel.name);

        Button setting = (Button) getActivity().findViewById(R.id.usr_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), com.example.elsie.framelayout.Setting.PersonalSettingActivity.class);
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
                //Intent intent=new Intent();
                //ComponentName comp = new ComponentName("SettingFragement", "com.eg.android.AlipayGphone.AlipayLogin");
                //intent.setComponent(comp);
                //intent.setClassName("com.eg.android.AlipayGphone","com.eg.android.AlipayGphone.AlipayLogin");
                //intent.putExtra("other", "true");
                //intent.setAction("android.intent.action.VIEW");
                //startActivity(intent);
                //intent.setClassName("com.eg.android.AlipayGphone","com.eg.android.AlipayGphone.AlipayLogin");
                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.eg.android.AlipayGphone");
// 这里如果intent为空，就说名没有安装要跳转的应用嘛
                if (intent != null) {
                    // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
                    //intent.putExtra("name", "Liu xiang");
                    //intent.putExtra("birthday", "1983-7-13");
                    startActivity(intent);
                } else {
                    // 没有安装要跳转的app应用，提醒一下
                    Toast.makeText(getActivity().getApplicationContext(), "未安装支付宝", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
