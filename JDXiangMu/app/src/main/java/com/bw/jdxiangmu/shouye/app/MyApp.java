package com.bw.jdxiangmu.shouye.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by lenovo on 2018/3/12.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration imageLoaderConfiguration=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
        Fresco.initialize(this);

    }
}
