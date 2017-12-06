package com.example.elsie.framelayout.Setting;

import android.graphics.Bitmap;

/**
 * Created by sherry on 17-12-1.
 * 存储用户个人信息
 */

public class PersonalModel{
    private long id;
    private String name;
    private String mail;
    private Bitmap photo;

    long getId(){return id;}
    String getName(){return name;}
    String getMail(){return mail;}
    Bitmap getPhoto(){return photo;}
    void setId(long id){this.id=id;}
    void setName(String name){this.name=name;}
    void setMail(String mail){this.mail=mail;}
    void setPhoto(Bitmap photo){this.photo=photo;}
}
