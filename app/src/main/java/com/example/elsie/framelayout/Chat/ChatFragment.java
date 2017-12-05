package com.example.elsie.framelayout.Chat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elsie on 2017/11/3.
 * 聊天区界面
 */

public class ChatFragment extends android.support.v4.app.Fragment {

    private List<comment> mChatList=new ArrayList<>();
    private RecyclerView chatRecyclerView;
    private ChatAdapter adapter;

    //加载聊天界面
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat, container, false);

        FloatingActionButton fab =(FloatingActionButton)view.findViewById(R.id.add_comment);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Toast.makeText(view.getContext(), "添加评论按钮", Toast.LENGTH_SHORT).show();
                                   }
                               });

        chatRecyclerView = (RecyclerView) view.findViewById(R.id.chat_activity);
        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        chatRecyclerView.setLayoutManager(layoutManager);


        initMsgs();//初始化消息数据
        chatRecyclerView.setLayoutManager(layoutManager);
        adapter =new ChatAdapter(mChatList);
        chatRecyclerView.setAdapter(adapter);
        return view;
    }

    private void initMsgs() {
        comment chat1=new comment("Tom","12:00","TestTest",0,1,0);
        mChatList.add(chat1);
        comment chat2=new comment("Tom","12:00","TestTest",3,0,1);
        mChatList.add(chat2);
        comment chat3=new comment("Tom","12:00","TestTest",1,0,0);
        mChatList.add(chat3);
        comment chat4=new comment("Tom","12:00","TestTest",2,0,0);
        mChatList.add(chat4);
        comment chat5=new comment("Tom","12:00","TestTest",4,0,0);
        mChatList.add(chat5);
        mChatList.add(chat5);
        mChatList.add(chat5);
    }
}
