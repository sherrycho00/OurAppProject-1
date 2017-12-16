package com.example.elsie.framelayout.Chat;

import java.util.List;

/**
 * Created by Lenovo on 2017/12/15.
 */

public class moment {
    //标签
    public static final int L_TYPE_DISLIKE=0;//吐槽
    public static final int L_TYPE_COMMEND=1;//推荐
    public static final int L_TYPE_DATE=2;//约饭
    public static final int L_TYPE_FIND=3;//失物招领
    public static final int L_TYPE_ELSE=4;//其他

    //点赞
    public static final int Z_TYPE_LIKE=0;//赞
    public static final int Z_TYPE_DISLIKE=1;


    private int id;
    private String chat_name;
    private String chat_time;
    private String chat_content;
    private int l_type;//标签
    private int z_type;//自己有没有赞
    private int z_num;//大家赞的数量
    private List<String> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getChat_time() {
        return chat_time;
    }

    public void setChat_time(String chat_time) {
        this.chat_time = chat_time;
    }

    public String getChat_content() {
        return chat_content;
    }

    public void setChat_content(String chat_content) {
        this.chat_content = chat_content;
    }

    public int getL_type() {
        return l_type;
    }

    public void setL_type(int l_type) {
        this.l_type = l_type;
    }

    public int getZ_type() {
        return z_type;
    }

    public void setZ_type(int z_type) {
        this.z_type = z_type;
    }

    public int getZ_num() {
        return z_num;
    }

    public void setZ_num(int z_num) {
        this.z_num = z_num;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
