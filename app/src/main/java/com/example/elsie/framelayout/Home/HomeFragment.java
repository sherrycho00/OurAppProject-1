package com.example.elsie.framelayout.Home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.elsie.framelayout.R;
import com.oragee.banners.BannerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elsie on 2017/11/3.
 * 首页界面
 */

public class HomeFragment extends android.support.v4.app.Fragment{
    private int[] imgs = {R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};

    private List<View> viewList;

    BannerView bannerView;

//    用于引入我们的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home,container,false);
        viewList = new ArrayList<View>();

        for (int i = 0; i < imgs.length; i++) {

            ImageView image = new ImageView(view.getContext());

            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式

            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

            image.setImageResource(imgs[i]);

            viewList.add(image);

        }

        bannerView = (BannerView) view.findViewById(R.id.banner);
        bannerView.startLoop(true);
        bannerView.setViewList(viewList);


////        ImageView imageView = (ImageView)view.findViewById(R.id.test);
//
//        new HttpImageDownload(imageView).execute();


        return view;
    }


    public class HttpImageDownload extends AsyncTask {

        private ImageView mImageView;

        public HttpImageDownload(ImageView imageView)
        {
            mImageView = imageView;
        }

        @Override
        protected Object doInBackground(Object[] params) {

            try {
                // 创建一个URL
                URL url = new URL("http://pic12.nipic.com/20110227/6728437_104023649000_2.jpg");

                // 从URL获取对应资源的 InputStream
                InputStream inputStream = url.openStream();
                // 用inputStream来初始化一个Bitmap 虽然此处是Bitmap，但是URL不一定非得是Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // 关闭 InputStream
                inputStream.close();

                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            // 此处的形参o，是doInBackground的返回值
            mImageView.setImageBitmap((Bitmap)o);
        }
    }
}
