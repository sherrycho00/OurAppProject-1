package com.example.elsie.framelayout.Chat;

import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Lenovo on 2017/11/12.
 */

public class comment {

    //标签
    public static final int L_TYPE_DISLIKE=0;//吐槽
    public static final int L_TYPE_COMMEND=1;//推荐
    public static final int L_TYPE_DATE=2;//约饭
    public static final int L_TYPE_FIND=3;//失物招领
    public static final int L_TYPE_ELSE=4;//其他

    //在线状态
    public static final int W_TYPE_ONLINE=0;//在线
    public static final int W_TYPE_OFFLINE=1;//离线

    //点赞
    public static final int Z_TYPE_LIKE=0;//赞
    public static final int Z_TYPE_DISLIKE=1;//

    private String chat_name;
    private String chat_time;
    private String chat_content;
    private int l_type=0;
    private int w_type=0;
    private int z_type=0;


    com.makeramen.roundedimageview.RoundedImageView chat_img;

    public comment(String chat_name,String chat_time,String chat_content,int l_type,int w_type,int z_type){
        this.chat_name=chat_name;
        this.chat_time=chat_time;
        this.chat_content=chat_content;
        this.l_type=l_type;
        this.w_type=w_type;
        this.z_type=z_type;
    }
    public String getChat_name(){
        return chat_name;
    }
    public String getChat_content(){
        return chat_content;
    }
    public String getChat_time(){
        return chat_time;
    }

    public int getL_Type(){
        return l_type;
    }
    public int getW_Type(){
        return w_type;
    }
    public int getZ_Type(){
        return z_type;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public void setChat_time(String chat_time) {
        this.chat_time = chat_time;
    }

    public void setChat_content(String chat_content) {
        this.chat_content = chat_content;
    }

    public void setL_type(int l_type) {
        this.l_type = l_type;
    }

    public void setW_type(int w_type) {
        this.w_type = w_type;
    }

    public void setZ_type(int z_type) {
        this.z_type = z_type;
    }
}
