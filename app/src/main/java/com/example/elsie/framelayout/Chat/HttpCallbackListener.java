package com.example.elsie.framelayout.Chat;

public interface HttpCallbackListener{
    void onFinish(String response);
    void onError(Exception e);
}
