package com.example.elsie.framelayout.Rank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elsie.framelayout.R;

import java.util.List;

/**
 * Created by Elsie on 2017/12/3.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

//    建立一个数据源
    private List<Product> DishList;

//    默认构造函数
    public MenuAdapter(List<Product> dishList) {
        DishList = dishList;
    }

    //        加载列表项布局
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //        加载列表项布局
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dish_item,parent,false);

        //        控件示例缓存
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    //	  对RecycleView子项进行操作
//    对RecyleView子项的数据进行赋值，在每个子项被滚动到屏幕内的时候执行
    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        Product dish = DishList.get(position);

        holder.dishPrice.setText("￥"+(int) dish.getFoodPrice());
        holder.dishName.setText(dish.getFoodName());
        holder.dishCommend.setText(dish.getSalesCount()+"人推荐");
//        holder.dishImageView.setImageResource(Integer.parseInt(dish.getImageUrl()));
        holder.dishImageView.setImageResource(R.drawable.dish);


    }

    @Override
    public int getItemCount() {
        return DishList.size();
    }

//     ViewHolder构造函数，用来获得组件
    public class ViewHolder extends RecyclerView.ViewHolder {
//    相应的变量
        ImageView dishImageView,likeDish,dislikeDish;
        TextView dishCommend,dishName,dishPrice;

        public ViewHolder(View itemView) {
            super(itemView);
//            从id获得对应的控件
            dishImageView = (ImageView) itemView.findViewById(R.id.dish_imageView);
            likeDish      = (ImageView) itemView.findViewById(R.id.like_dish_view);
            dislikeDish   = (ImageView) itemView.findViewById(R.id.dislike_dish_view);

            dishCommend   = (TextView) itemView.findViewById(R.id.dish_commend_people);
            dishName      = (TextView) itemView.findViewById(R.id.dish_name_view);
            dishPrice     = (TextView) itemView.findViewById(R.id.dish_price_view);

        }
    }
}
