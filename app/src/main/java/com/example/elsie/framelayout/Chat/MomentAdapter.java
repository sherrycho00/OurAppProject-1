package com.example.elsie.framelayout.Chat;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.elsie.framelayout.R.drawable.chat_lab_dislike;

/**
 * Created by Lenovo on 2017/12/15.
 */

public class MomentAdapter extends RecyclerView.Adapter<MomentAdapter.ViewHolder> {
    private List<moment> mMomentList;
    private CreateCommentDialog createCommentDialog;
    private String moment_comment="";
//    private String comments="";
    private int lock=0;

    static class ViewHolder extends RecyclerView.ViewHolder {
        com.makeramen.roundedimageview.RoundedImageView chat_img;
        TextView chat_name;
        TextView chat_time;
        TextView chat_content;

        LinearLayout chat_label_bg;
        TextView chat_label;

        LinearLayout back_msg;
        TextView like_num;

        LinearLayout like;
        ImageView click_like;

        ImageView comment;
        ImageView add_comment;

        LinearLayout comment_content;
        TextView com_content;

        LinearLayout add_comment_dialog;
        android.support.v7.widget.CardView moment_item;

        public ViewHolder(View view) {
            super(view);
            chat_img = (com.makeramen.roundedimageview.RoundedImageView) view.findViewById(R.id.chat_user);
            chat_name = (TextView) view.findViewById(R.id.chat_name);
            chat_time = (TextView) view.findViewById(R.id.chat_time);
            chat_content = (TextView) view.findViewById(R.id.chat_content);

            chat_label_bg = (LinearLayout) view.findViewById(R.id.chat_label_bg);
            chat_label = (TextView) view.findViewById(R.id.chat_label);

            back_msg = (LinearLayout) view.findViewById(R.id.back_msg);
            like_num = (TextView) view.findViewById(R.id.like_num);

            like = (LinearLayout) view.findViewById(R.id.like);
            click_like = (ImageView) view.findViewById(R.id.click_like);
            comment = (ImageView) view.findViewById(R.id.comment);
            add_comment=(ImageView)view.findViewById(R.id.a_comment);
            comment_content = (LinearLayout) view.findViewById(R.id.comment_contents);
            com_content = (TextView) view.findViewById(R.id.com_content);
            add_comment_dialog=(LinearLayout)view.findViewById(R.id.add_comment_dialog);
            moment_item=(android.support.v7.widget.CardView)view.findViewById(R.id.chat);
        }
    }
    public MomentAdapter(List<moment> momentList){
        mMomentList=momentList;
    }
    @Override
    public MomentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MomentAdapter.ViewHolder holder, int position) {
        final moment mom=mMomentList.get(position);
        holder.comment_content.setVisibility(View.GONE);//开始没有评论，隐藏
        holder.chat_img.setVisibility(View.VISIBLE);
        holder.chat_content.setText(mom.getChat_content());
        holder.chat_name.setText(mom.getChat_name());
        holder.chat_time.setText(mom.getChat_time());
        holder.like_num.setText(String.valueOf(String.valueOf(mom.getZ_num())));

        //标签信息
        if(mom.getL_type()==mom.L_TYPE_DISLIKE) {//吐槽
            holder.chat_label_bg.setBackgroundResource(R.drawable.chat_lab_dislike);
            holder.chat_label.setText("吐槽");
        }
        else if (mom.getL_type()==mom.L_TYPE_COMMEND) {//推荐
            holder.chat_label_bg.setBackgroundResource(R.drawable.chat_lab_commend);
            holder.chat_label.setText("推荐");
        }
        else if (mom.getL_type()==mom.L_TYPE_DATE){//约饭
            holder.chat_label_bg.setBackgroundResource(R.drawable.chat_lab_date);
            holder.chat_label.setText("约饭");

        }
        else if (mom.getL_type()==mom.L_TYPE_FIND) {//失物招领
            holder.chat_label_bg.setBackgroundResource(R.drawable.chat_lab_find);
            holder.chat_label.setText("失物招领");
        }
        else if (mom.getL_type()==mom.L_TYPE_ELSE) {//其他
            holder.chat_label_bg.setBackgroundResource(R.drawable.chat_lab_else);
            holder.chat_label.setText("其他");
        }

        if(mom.getZ_type()==mom.Z_TYPE_LIKE){
            holder.click_like.setImageResource(R.drawable.chat_like);
        }
        else if(mom.getZ_type()==mom.Z_TYPE_DISLIKE) {
            holder.click_like.setImageResource(R.drawable.chat_no_like);
        }

        holder.click_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mom.getZ_type()==mom.Z_TYPE_LIKE){
                    holder.click_like.setImageResource(R.drawable.chat_no_like);
                    mom.setZ_type(mom.Z_TYPE_DISLIKE);
                    int tmp=0;
                    tmp=mom.getZ_num()-1;
                    mom.setZ_num(tmp);
                    final int id=mom.getId();
                    final int num=tmp;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            HttpURLConnection connection = null;
                            BufferedReader reader = null;
                            try {
                                URL url = new URL("http://10.0.2.2:8080/ourProject/php_mysql_update.php?num="+Integer.toString(num)+"&i="+Integer.toString(id));
//http://10.0.2.2:8080/php_mysql_select.php
                                connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("GET");

                                InputStream in = connection.getInputStream();
                                new BufferedReader(new InputStreamReader(in));
//                                String url1 = "http://10.0.2.2:8080/ourProject/temp.json";
//                                downloadBinder.startDownload(url1);
//                            StringBuilder response = new StringBuilder();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    holder.like_num.setText(String.valueOf(tmp));
//                    Toast.makeText(view.getContext(),"clike",Toast.LENGTH_SHORT).show();
                }
                else if(mom.getZ_type()==mom.Z_TYPE_DISLIKE) {
                    holder.click_like.setImageResource(R.drawable.chat_like);
                    mom.setZ_type(mom.Z_TYPE_LIKE);
                    int tmp=0;
                    tmp=mom.getZ_num()+1;
                    mom.setZ_num(tmp);
                    final int id=mom.getId();
                    final int num=tmp;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            HttpURLConnection connection = null;
                            BufferedReader reader = null;
                            try {
                                URL url = new URL("http://10.0.2.2:8080/ourProject/php_mysql_update.php?num="+Integer.toString(num)+"&i="+Integer.toString(id));
//http://10.0.2.2:8080/php_mysql_select.php
                                connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("GET");

                                InputStream in = connection.getInputStream();
                                new BufferedReader(new InputStreamReader(in));
//                                String url1 = "http://10.0.2.2:8080/ourProject/temp.json";
//                                downloadBinder.startDownload(url1);
//                            StringBuilder response = new StringBuilder();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    holder.like_num.setText(String.valueOf(tmp));
//                    Toast.makeText(view.getContext(),"clike",Toast.LENGTH_SHORT).show();
                }

            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int isThis = mom.getId();
                String comments = "";
                holder.comment_content.setVisibility(holder.comment_content.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
//              if(comments.equals("")&&moment_comment.equals("")) {
//                      holder.com_content.setText("暂无评论");
//                  }
//                else if(comments.equals("")&&!moment_comment.equals("")){
//                    comments = comments + moment_comment;
//                    holder.com_content.setText(comments);
//                    moment_comment="";
//                }
//                else if(!comments.equals("")&&!moment_comment.equals("")){
//                    comments = comments +"\r\n"+ moment_comment;
//                    holder.com_content.setText(comments);
//                    moment_comment="";
//                }
//                else{
//                    holder.com_content.setText(comments);
//                }
                if (mom.getComments().isEmpty() && moment_comment.equals("")) {
                    holder.com_content.setText("暂无评论");
                } else if (!moment_comment.equals("")) {
                    List<String> tmp = mom.getComments();
                    tmp.add(moment_comment);
                    mom.setComments(tmp);
                    String up_com = "";
                    int s = 0;
                    for (String t : tmp) {
                        if (s == 0) {
                            up_com ="[\""+t+"\"";
                            s = 1;
                        } else {
                            up_com = up_com+",\"" + t +"\"";
                        }
                    }
//                    for (String t : tmp) {
//                        if (s == 0) {
//                            up_com =t;
//                            s = 1;
//                        } else {
//                            up_com = up_com+"," + t ;
//                        }
//                    }

                    up_com = up_com + "]";
                    final int id = mom.getId();
//                    String url="http://10.0.2.2:8080/ourProject/php_mysql_update.php?com="+up_com+ "&i=" + Integer.toString(id);
                    final String u_c=up_com;
                    final String finalUp_com = up_com;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            HttpURLConnection connection = null;
                            BufferedReader reader = null;
                            try {
//
                                URL url = new URL("http://10.0.2.2:8080/ourProject/php_mysql_update.php?com="+finalUp_com+"&i=9");
//http://10.0.2.2:8080/php_mysql_select.php
                                connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("GET");
                                InputStream in = connection.getInputStream();
                                new BufferedReader(new InputStreamReader(in));
//                                String url1 = "http://10.0.2.2:8080/ourProject/temp.json";
//                                downloadBinder.startDownload(url1);
//                            StringBuilder response = new StringBuilder();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    moment_comment = "";
                    int start = 0;
                    for (String m : mom.getComments()) {
                        if (start == 0) {
                            comments = comments + m;
                            start = 1;
                        } else {
                            comments = comments + "\r\n" + m;
                        }
                    }
                    holder.com_content.setText(comments);
                } else {
                    int start = 0;
                    for (String m : mom.getComments()) {
                        if (start == 0) {
                            comments = comments + m;
                            start = 1;
                        } else {
                            comments = comments + "\r\n" + m;
                        }
                    }
                    holder.com_content.setText(comments);
                }
            }
        });

        holder.add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.comment_content.setVisibility(View.GONE);
                showEditDialog(view);


            }
        });
    }
    public void showEditDialog(View view) {
        createCommentDialog = new CreateCommentDialog((Activity) view.getContext(),R.style.loading_dialog,onClickListener);
        createCommentDialog.show();
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.send_comment:
                    moment_comment = createCommentDialog.content.getText().toString().trim();
                    if(!moment_comment.equals("")) {
                        moment_comment="Tom:"+moment_comment;
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                HttpURLConnection connection=null;
//                                BufferedReader reader=null;
//                                try{
//                                    URL url=new URL("http://10.0.2.2:8080/php_mysql.php?n="+chat_name
//                                            +"&t="+chat_time+"&c="+chat_content+"&l="+l +"&line="+w+"&like="+z);
//                                    connection=(HttpURLConnection)url.openConnection();
//                                    connection.setRequestMethod("GET");
//
//                                    InputStream in=connection.getInputStream();
//                                    reader=new BufferedReader(new InputStreamReader(in));
//                                    StringBuilder response=new StringBuilder();
//                                }catch (Exception e){
//                                    e.printStackTrace();
//                                }
//                            }
//                        }).start();

//                        mChatList.add(0,chat);//插入到开头位置
//                        chatRecyclerView.setLayoutManager(layoutManager);

//                        chatRecyclerView.setAdapter(adapter);
                        createCommentDialog.content.setText("");
                        createCommentDialog.hide();

                    }
                    else{
                        Toast.makeText(v.getContext(), "empty content" + moment_comment, Toast.LENGTH_SHORT).show();
                        createCommentDialog.hide();

                    }
                    break;

            }
        }
    };
    @Override
    public int getItemCount() {
        return mMomentList.size();
    }
}


