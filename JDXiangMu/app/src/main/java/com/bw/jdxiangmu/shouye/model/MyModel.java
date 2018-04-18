package com.bw.jdxiangmu.shouye.model;

import android.os.Handler;
import android.os.Message;


import com.bw.jdxiangmu.shouye.bean.JingDong;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/3/12.
 */

public class MyModel {

    public interface NewModel{
        void OnSuccess(JingDong jingDong);
    }
    NewModel newModel;
    public void getNewModel(NewModel newModel){
        this.newModel=newModel;
    }

    Handler handler =new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                JingDong jingDong= (JingDong) msg.obj;
                if(newModel!=null){
                    newModel.OnSuccess(jingDong);

                }

            }
        }
    };
    public void getModel(String s){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(s).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                JingDong jingDong = gson.fromJson(string, JingDong.class);
                Message message=new Message();
                message.what=0;
                message.obj=jingDong;
                handler.sendMessage(message);
            }
        });
    }
}
