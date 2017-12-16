package com.example.elsie.framelayout.Setting;

import cn.bmob.v3.BmobObject;

/**
 * Created by sherry on 17-12-1.
 * 存储用户个人信息
 */

public class MyUser extends BmobObject {
    /**
     * 登录名
     */
    private String User_name;

    /**
     * 昵称
     */
    private String nickname;

    private String head_url;

    public String getUser_name(){
        return User_name;
    }
    public void setUser_name(String User_name) {
        this.User_name=User_name;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public  String getHead_url(){return head_url;}
    public void setHead_url(String head_url){
        this.head_url=head_url;
    }
}
