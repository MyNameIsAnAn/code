package com.bw.jdxiangmu.shouye.app;


import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by lenovo on 2018/3/12.
 */

public class MyImg extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String s= (String) path;
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(s,imageView);
    }
}
