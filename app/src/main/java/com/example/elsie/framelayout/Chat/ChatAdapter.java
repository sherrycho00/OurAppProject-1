package com.example.elsie.framelayout.Chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elsie.framelayout.R;

import java.util.List;

/**
 * Created by Lenovo on 2017/11/12.
 * 聊天的适配器
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<comment> mComList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        com.makeramen.roundedimageview.RoundedImageView chat_img;
        TextView chat_name;
        TextView chat_time;
        TextView chat_content;

        LinearLayout chat_state_online;
        LinearLayout chat_state_offline;

        LinearLayout chat_label_dislike;
        LinearLayout chat_label_commend;
        LinearLayout chat_label_date;
        LinearLayout chat_label_find;
        LinearLayout chat_label_else;

        LinearLayout chat_like;
        LinearLayout chat_no_like;
        LinearLayout chat_comment;
        TextView like_num;
        public ViewHolder(View view){
            super(view);
            chat_img=(com.makeramen.roundedimageview.RoundedImageView)view.findViewById(R.id.chat_user);
            chat_name=(TextView) view.findViewById(R.id.chat_name);
            chat_time=(TextView) view.findViewById(R.id.chat_time);
            chat_content=(TextView)view.findViewById(R.id.chat_content);
            chat_state_online=(LinearLayout) view.findViewById(R.id.chat_state_online);
            chat_state_offline=(LinearLayout) view.findViewById(R.id.chat_state_offline);

            chat_label_dislike=(LinearLayout) view.findViewById(R.id.chat_label_dislike);
            chat_label_commend=(LinearLayout) view.findViewById(R.id.chat_label_commend);
            chat_label_date=(LinearLayout) view.findViewById(R.id.chat_label_date);
            chat_label_find=(LinearLayout) view.findViewById(R.id.chat_label_find);
            chat_label_else=(LinearLayout) view.findViewById(R.id.chat_label_else);

            chat_like=(LinearLayout) view.findViewById(R.id.like);
            chat_no_like=(LinearLayout) view.findViewById(R.id.no_like);
            chat_comment=(LinearLayout)view.findViewById(R.id.comment);
            like_num=(TextView)view.findViewById(R.id.like_num);

        }

    }

    public ChatAdapter(List<comment> chatList){
        mComList=chatList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final comment com=mComList.get(position);
        holder.chat_img.setVisibility(View.VISIBLE);
        holder.chat_content.setText(com.getChat_content());
        holder.chat_name.setText(com.getChat_name());
        holder.chat_time.setText(com.getChat_time());

        //标签信息
        if(com.getL_Type()==com.L_TYPE_DISLIKE){//吐槽
           holder.chat_label_dislike.setVisibility(View.VISIBLE);
           holder.chat_label_commend.setVisibility(View.GONE);
            holder.chat_label_date.setVisibility(View.GONE);
            holder.chat_label_find.setVisibility(View.GONE);
            holder.chat_label_else.setVisibility(View.GONE);
        }
        else if (com.getL_Type()==com.L_TYPE_COMMEND){//推荐
            holder.chat_label_dislike.setVisibility(View.GONE);
            holder.chat_label_commend.setVisibility(View.VISIBLE);
            holder.chat_label_date.setVisibility(View.GONE);
            holder.chat_label_find.setVisibility(View.GONE);
            holder.chat_label_else.setVisibility(View.GONE);
        }
        else if (com.getL_Type()==com.L_TYPE_DATE){//约饭
            holder.chat_label_dislike.setVisibility(View.GONE);
            holder.chat_label_commend.setVisibility(View.GONE);
            holder.chat_label_date.setVisibility(View.VISIBLE);
            holder.chat_label_find.setVisibility(View.GONE);
            holder.chat_label_else.setVisibility(View.GONE);
        }
        else if (com.getL_Type()==com.L_TYPE_FIND){//失物招领
            holder.chat_label_dislike.setVisibility(View.GONE);
            holder.chat_label_commend.setVisibility(View.GONE);
            holder.chat_label_date.setVisibility(View.GONE);
            holder.chat_label_find.setVisibility(View.VISIBLE);
            holder.chat_label_else.setVisibility(View.GONE);
        }
        else if (com.getL_Type()==com.L_TYPE_ELSE){//其他
            holder.chat_label_dislike.setVisibility(View.GONE);
            holder.chat_label_commend.setVisibility(View.GONE);
            holder.chat_label_date.setVisibility(View.GONE);
            holder.chat_label_find.setVisibility(View.GONE);
            holder.chat_label_else.setVisibility(View.VISIBLE);
        }

        //状态信息
        if(com.getW_Type()==com.W_TYPE_OFFLINE){//离线
            //如果用户离线，则显示离线消息布局，将在线的消息布局隐藏
            holder.chat_state_online.setVisibility(View.GONE);
            holder.chat_state_offline.setVisibility(View.GONE);
        }else if (com.getW_Type()==com.W_TYPE_ONLINE){//在线
            //如果用户在线，则显示在线消息布局，将离线的消息布局隐藏
            holder.chat_state_online.setVisibility(View.GONE);
            holder.chat_state_offline.setVisibility(View.GONE);
        }

        //点赞信息
        if(com.getZ_Type()==com.Z_TYPE_DISLIKE){//没被点赞
            //
            holder.chat_no_like.setVisibility(View.VISIBLE);
            holder.chat_like.setVisibility(View.GONE);

        }else if (com.getW_Type()==com.Z_TYPE_LIKE){//赞
            //如果用户在线，则显示在线消息布局，将离线的消息布局隐藏
            holder.chat_like.setVisibility(View.VISIBLE);
            holder.chat_no_like.setVisibility(View.GONE);
        }

        holder.chat_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int tmp=com.getLike_num();
//                tmp++;
//                com.setLike_num(tmp);
//                holder.like_num.setText(tmp);
                holder.chat_no_like.setVisibility(view.VISIBLE);
                holder.chat_like.setVisibility(view.GONE);
            }
        });
        holder.chat_no_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.chat_no_like.setVisibility(view.GONE);
                holder.chat_like.setVisibility(view.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mComList.size();
    }



}
