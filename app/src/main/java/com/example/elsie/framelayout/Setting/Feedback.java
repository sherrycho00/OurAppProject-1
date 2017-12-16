package com.example.elsie.framelayout.Setting;

import cn.bmob.v3.BmobObject;

/**
 * Created by sherry on 17-12-12.
 */

public class Feedback extends BmobObject{
    private String msg;
    private String contact;
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public String getContact(){
        return contact;
    }
    public void setContact(String contact){
        this.contact=contact;
    }
}
