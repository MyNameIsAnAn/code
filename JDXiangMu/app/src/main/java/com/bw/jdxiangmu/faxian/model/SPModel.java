package com.bw.jdxiangmu.faxian.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bw.jdxiangmu.faxian.bean.SPInfo;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Created by lenovo on 2018/4/10.
 */

public class SPModel {

    public interface OnListener{

        void OnSuccess(SPInfo spInfo);
    }
    OnListener onListener;
    public void getOnListener(OnListener onListener){
        this.onListener=onListener;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
               SPInfo spInfo= (SPInfo) msg.obj;
                if(onListener!=null){
                    onListener.OnSuccess(spInfo);
                }

            }
        }
    };

    public void getModel(){

        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url("http://is.snssdk.com/neihan/stream/mix/v1?mpic=1&webp=1&essence=1&content_type=-101&message_cursor=-1&longitude=116.4121485&latitude=39.9365054&am_longitude=116.41828&am_latitude=39.937848&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82&am_loc_time=1483686438786&count=30&min_time=1483929653&screen_width=1080&iid=7164180604&device_id=34822199408&ac=wifi&channel=baidu&aid=7&app_name=joke_essay&version_code=590&version_name=5.9.0&device_platform=android&ssmix=a&device_type=Nexus%2B5&device_brand=google&os_api=25&os_version=7.1&uuid=359250050588035&openudid=12645e537a2f0f25&manifest_version_code=590&resolution=1080*1776&dpi=480&update_version_code=5903").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                String string = body.string();
                Gson gson=new Gson();
                SPInfo spInfo = gson.fromJson(string,SPInfo.class);
                Message message=new Message();
                message.what=0;
                message.obj=spInfo;
                handler.sendMessage(message);
                Log.d("SP","getModel");
            }
        });

    }


}
