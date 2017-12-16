package com.example.elsie.framelayout.Setting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import static com.example.elsie.framelayout.LoginActivity.muserid;
import static com.example.elsie.framelayout.Setting.PersonalSettingActivity.mn_name;

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

        if(mn_name!=null){
        mnick_name.setText(mn_name);}
        else{
            mnick_name.setText(muserid);}

        //查询数据库中的用户信息
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.addWhereEqualTo("User_name",muserid);
        query.findObjects(getActivity(), new FindListener<MyUser>() {
            @Override
            public void onSuccess(List<MyUser> list) {
                for(MyUser myUser:list){
                    if(myUser.getHead_url()==null){
                        /*mimage_user.setImageResource(R.drawable.userimg);*/
                    }
                    else {
                        Bitmap bitmap = BitmapFactory.decodeFile(myUser.getHead_url());
                        mheader_img.setImageBitmap(bitmap);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                //查询失败
            }
        });

        Button favorite=(Button)getActivity().findViewById(R.id.favorites);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.favorites:
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), FavoriteActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.push_left_in,
                                R.anim.push_left_out);
                        break;

                }
            }
        });
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
                //获取支付宝包名
                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.eg.android.AlipayGphone");

                if (intent != null) {
                    startActivity(intent);
                } else {
                    // 没有安装要跳转的app应用，提醒一下
                    Toast.makeText(getActivity().getApplicationContext(), "未安装支付宝", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
