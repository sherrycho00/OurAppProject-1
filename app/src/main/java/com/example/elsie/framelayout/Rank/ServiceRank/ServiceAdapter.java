package com.example.elsie.framelayout.Rank.ServiceRank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

import java.util.List;

/**
 * Created by Elsie on 2017/12/13.
 */

public class ServiceAdapter  extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>  {

    List<WorkerInfo> workerInfos;

//    默认构造函数
    public ServiceAdapter(List<WorkerInfo> w) {
        workerInfos = w;
    }

    //        加载列表项布局
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //        加载列表项布局
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.service_item,parent,false);

        //        控件示例缓存
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    //	  对RecycleView子项进行操作
    @Override
    public void onBindViewHolder(final ServiceAdapter.ViewHolder holder, int position) {

        final WorkerInfo workerInfo = workerInfos.get(position);

        final boolean[] isClick = {false,false};

//        holder.workerImg.setImageResource(workerInfo.getWorkerImg());
        holder.workerImg.setImageResource(R.drawable.workerimg);
        holder.window.setText(workerInfo.getWindow()+"号");
        holder.workerID.setText(workerInfo.getWorkerID());
        holder.workerName.setText(workerInfo.getWorkerName());
        holder.score.setText(workerInfo.getScore()+"人点赞");
        holder.smile.setImageResource(R.drawable.smile);
        holder.upset.setImageResource(R.drawable.disapointed);

//        点击笑脸
        holder.smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int flag = 0;
                int num = 0;
//                实现只能点击一次
//                点击赞
                if (!isClick[0]) {

                    num = workerInfo.getScore();
                    num ++;
                    workerInfo.setScore(num);

                    holder.score.setText(workerInfo.getScore()+"人点赞");

                    Toast.makeText(v.getContext(),"click like",Toast.LENGTH_LONG).show();
//                换图片
                    holder.smile.setImageResource(R.drawable.smile_filling);

                    isClick[0] = true;
                    flag = 1;
                }

//                取消赞
                if (isClick[0] && flag == 0) {
                    num = workerInfo.getScore();
                    num --;
                   workerInfo.setScore(num);
                    holder.score.setText(workerInfo.getScore()+"人点赞");
                    Toast.makeText(v.getContext(),"cancle click like",Toast.LENGTH_LONG).show();
//                换图片
                    holder.smile.setImageResource(R.drawable.smile);

                    isClick[0] = false;
                }

////                将数据写到json中
//                if (num != 0) {
//                    int key = dish.getID();
//                    String floor = null;
//                    switch (mFloorData.getFloor()) {
//                        case 1:
//                            floor = "一楼";
//                            break;
//                        case 2:
//                            floor = "二楼";
//                            break;
//                        case 3:
//                            floor = "三楼";
//                            break;
//                        case 4:
//                            floor = "清真";
//                            break;
//                        case 5:
//                            floor = "红楼";
//                            break;
//                        default:
//                            break;
//                    }
//
//                }



            }
        });

//        点击upset
        holder.upset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int flag = 0;
                int num = 0;
//                实现只能点击一次
//                点击不赞
                if (!isClick[0]) {

                    num = workerInfo.getScore();
                    num --;
                    workerInfo.setScore(num);

                    holder.score.setText(workerInfo.getScore()+"人点赞");

                    Toast.makeText(v.getContext(),"click unlike",Toast.LENGTH_LONG).show();
//                换图片
                    holder.upset.setImageResource(R.drawable.disapointed_filling);

                    isClick[0] = true;
                    flag = 1;
                }

//                取消赞
                if (isClick[0] && flag == 0) {
                    num = workerInfo.getScore();
                    num ++;
                    workerInfo.setScore(num);
                    holder.score.setText(workerInfo.getScore()+"人点赞");
                    Toast.makeText(v.getContext(),"cancle click unlike",Toast.LENGTH_LONG).show();
//                换图片
                    holder.upset.setImageResource(R.drawable.disapointed);

                    isClick[0] = false;
                }

////                将数据写到json中
//                if (num != 0) {
//                    int key = dish.getID();
//                    String floor = null;
//                    switch (mFloorData.getFloor()) {
//                        case 1:
//                            floor = "一楼";
//                            break;
//                        case 2:
//                            floor = "二楼";
//                            break;
//                        case 3:
//                            floor = "三楼";
//                            break;
//                        case 4:
//                            floor = "清真";
//                            break;
//                        case 5:
//                            floor = "红楼";
//                            break;
//                        default:
//                            break;
//                    }
//
//                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return workerInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView workerImg,smile,upset;
        TextView  window,workerID,workerName,score;
        public ViewHolder(View itemView) {
            super(itemView);

            workerImg = (ImageView)itemView.findViewById(R.id.workerImg);
            window = (TextView) itemView.findViewById(R.id.windowNum);
            workerID = (TextView) itemView.findViewById(R.id.workerID);
            workerName = (TextView) itemView.findViewById(R.id.workerName);
            score = (TextView) itemView.findViewById(R.id.score);

            smile = (ImageView) itemView.findViewById(R.id.smile);
            upset = (ImageView) itemView.findViewById(R.id.upset);

        }
    }
}
