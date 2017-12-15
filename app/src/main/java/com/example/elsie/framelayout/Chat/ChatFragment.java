package com.example.elsie.framelayout.Chat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.elsie.framelayout.DownloadService;
import com.example.elsie.framelayout.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Elsie on 2017/11/3.
 * 聊天区界面
 */

public class ChatFragment extends android.support.v4.app.Fragment{

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(DownloadService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private List<comment> mChatList=new ArrayList<>();
    private RecyclerView chatRecyclerView;
    private ChatAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton fab;
    private FloatingActionButton refresh;
    private RelativeLayout AddChat;
    private boolean isAdd = false;
    private CreateChatDialog createChatDialog;
    private int[] llId = new int[]{R.id.ll01,R.id.ll02,R.id.ll03,R.id.ll04,R.id.ll05};
    private LinearLayout[] ll = new LinearLayout[llId.length];

    private SwipeRefreshLayout swipeRefreshLayout;
    private String chat_name;
    private String chat_time;
    private String chat_content;
    private int lab=0;
    private int w_type;
    private int z_type;

    private static List<ChatTemp> chatList;

    private int[] fabId = new int[]{R.id.sub_add_date,R.id.sub_add_find,R.id.sub_add_else,R.id.sub_add_commend,R.id.sub_add_dislike};
    private FloatingActionButton[] sub_fab = new FloatingActionButton[fabId.length];


    //加载聊天界面
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.chat, container, false);

        Intent intent = new Intent(view.getContext(), DownloadService.class);
        view.getContext().startService(intent);
        view.getContext().bindService(intent, connection, BIND_AUTO_CREATE);

        initViews(view);

//        {R.id.sub_add_date,R.id.sub_add_find,R.id.sub_add_else,R.id.sub_add_commend,R.id.sub_add_dislike};
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       isAdd = !isAdd;

                                       AddChat.setVisibility(isAdd ? View.VISIBLE : View.GONE);
                                       Toast.makeText(view.getContext(), "发表内容按钮", Toast.LENGTH_SHORT).show();
                                       if(isAdd)
                                       {
//                                           约饭
                                           sub_fab[0].setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   lab=2;
                                                   showEditDialog(view);
                                                   hideFABMenu();
                                               }
                                           });
//                                           失物招领
                                           sub_fab[1].setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   lab=3;
                                                   showEditDialog(view);
                                                   hideFABMenu();
                                               }
                                           });
//                                           其他
                                           sub_fab[2].setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   lab=4;
                                                   showEditDialog(view);
                                                   hideFABMenu();
                                               }
                                           });
//                                           推荐
                                           sub_fab[3].setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   lab=1;
                                                   showEditDialog(view);
                                                   hideFABMenu();
                                               }
                                           });
//                                           吐槽
                                           sub_fab[4].setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   lab=0;
                                                   showEditDialog(view);
                                                   hideFABMenu();
                                               }
                                           });
                                       }

                                   }
                               });
        initMsgs(view);//初始化消息数据

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream in=null;
                File file=null;

                BufferedReader reader=null;
                StringBuilder content=new StringBuilder();
                String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                file=new File(directory+"/temp.json");
                if(file.exists()){
                    file.delete();
                    Toast.makeText(view.getContext(),"delete file",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(view.getContext(),"refreshing...",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection connection = null;
                        BufferedReader reader = null;

                        try {
                            URL url = new URL("http://10.0.2.2:8080/php_mysql_select.php");

                            connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");

                            InputStream in = connection.getInputStream();
                            new BufferedReader(new InputStreamReader(in));
                            String url1 = "http://10.0.2.2:8080/temp.json";
                            downloadBinder.startDownload(url1);
//                            StringBuilder response = new StringBuilder();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                mChatList.clear();
//                等新文件下载完毕后再更新
                while(!file.exists()){

                }
                if(file.exists()){
                    initMsgs(view);
                }

                chatRecyclerView.setLayoutManager(layoutManager);
                adapter = new ChatAdapter(mChatList);
                chatRecyclerView.setAdapter(adapter);
                Toast.makeText(view.getContext(),"refreshing end...",Toast.LENGTH_SHORT).show();
            }
        });
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(view.getContext(),"refreshing...",Toast.LENGTH_SHORT).show();
//                adapter.notifyDataSetChanged();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });


        chatRecyclerView.setLayoutManager(layoutManager);
        adapter =new ChatAdapter(mChatList);
        chatRecyclerView.setAdapter(adapter);
        return view;
    }

    private void initViews(View view) {
        fab=(FloatingActionButton)view.findViewById(R.id.add_comment);
        chatRecyclerView = (RecyclerView) view.findViewById(R.id.chat_activity);
        layoutManager=new LinearLayoutManager(view.getContext());
        chatRecyclerView.setLayoutManager(layoutManager);
        for (int i = 0; i < llId.length;i++){
            ll[i] = (LinearLayout)view.findViewById(llId[i]);
        }
        for (int i = 0;i < fabId.length; i++){
            sub_fab[i] = (FloatingActionButton)view.findViewById(fabId[i]);
        }
        AddChat=(RelativeLayout)view.findViewById(R.id.add_chat);
        refresh=(FloatingActionButton)view.findViewById(R.id.refresh);
//        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
//        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);

    }

    private void hideFABMenu(){
        AddChat.setVisibility(View.GONE);
        isAdd = false;
    }
    private void initMsgs(View view) {
        String inputText=external_load();

        if(!TextUtils.isEmpty(inputText)){
            Toast.makeText(view.getContext(),"Restoring succeed",Toast.LENGTH_SHORT).show();
            parseJSONWithGSON(inputText);
        }
    }
    public void showEditDialog(View view) {
        createChatDialog = new CreateChatDialog((Activity) view.getContext(),R.style.loading_dialog,onClickListener);
        createChatDialog.setLab(lab);
        createChatDialog.show();
    }

    public String external_load(){
    FileInputStream in=null;
    File file=null;

    BufferedReader reader=null;
    StringBuilder content=new StringBuilder();
    String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    file=new File(directory+"/temp.json");
    if(file.exists()){
        try {
            //读数据的流
            FileInputStream is = new FileInputStream(file);
            reader=new BufferedReader(new InputStreamReader(is));
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }}
    return content.toString();
}

    private void parseJSONWithGSON(String jsonData) {
        Gson gson=new Gson();
        chatList=gson.fromJson(jsonData,new TypeToken<List<ChatTemp>>(){}.getType());
        Collections.reverse(chatList);
        for(ChatTemp chat_tmp:chatList)
        {
            if(!chat_tmp.getChat_name().equals("end")){
            lab=Integer.valueOf(chat_tmp.getS_l());
            comment chat=new comment(chat_tmp.getChat_name(),chat_tmp.getChat_time(),chat_tmp.getChat_content(),lab,1,0);
            mChatList.add(chat);}
        }
    }




//    添加内容功能
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
            SimpleDateFormat format=new SimpleDateFormat("HH:mm");
            Date d1=new Date(time);
            chat_time=format.format(d1);
            chat_name="Peter";
            w_type=1;
            z_type=0;
            final String w=Integer.toString(w_type);
            final String z=Integer.toString(z_type);
            final String l=Integer.toString(lab);
            switch (v.getId()) {
                case R.id.send_chat:
                    chat_content = createChatDialog.content.getText().toString().trim()+"\r\n"+"hhh";
                    if(!chat_content.equals("")) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                HttpURLConnection connection=null;
                                BufferedReader reader=null;
                                try{
                                    URL url=new URL("http://10.0.2.2:8080/php_mysql.php?n="+chat_name
                                            +"&t="+chat_time+"&c="+chat_content+"&l="+l +"&line="+w+"&like="+z);
                                    connection=(HttpURLConnection)url.openConnection();
                                    connection.setRequestMethod("GET");

                                    InputStream in=connection.getInputStream();
                                    reader=new BufferedReader(new InputStreamReader(in));
                                    StringBuilder response=new StringBuilder();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        comment chat = new comment("Tom", chat_time, chat_content, lab, 1, 0);
                        mChatList.add(0,chat);//插入到开头位置
                        chatRecyclerView.setLayoutManager(layoutManager);
                        adapter = new ChatAdapter(mChatList);
                        chatRecyclerView.setAdapter(adapter);
                        createChatDialog.content.setText("");
                        createChatDialog.hide();
                    }
                    else{
                        Toast.makeText(v.getContext(), "empty content" + chat_content, Toast.LENGTH_SHORT).show();
                        createChatDialog.hide();
                    }
                    break;

            }
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        FileInputStream in=null;
        File file=null;

        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        file=new File(directory+"/temp.json");
        if(file.exists()){
            file.delete();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://10.0.2.2:8080/php_mysql_select.php");

                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    InputStream in = connection.getInputStream();
                    new BufferedReader(new InputStreamReader(in));
                    String url1 = "http://10.0.2.2:8080/temp.json";
                    downloadBinder.startDownload(url1);
//                            StringBuilder response = new StringBuilder();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
