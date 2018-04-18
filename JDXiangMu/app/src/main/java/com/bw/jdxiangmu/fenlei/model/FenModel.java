package com.bw.jdxiangmu.fenlei.model;

import android.os.Handler;
import android.os.Message;

import com.bw.jdxiangmu.fenlei.bean.FenLei;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/3/9.
 */

public class FenModel {

    public interface NewFenModel{

        void getSuccess(FenLei fenlei);
    }
    NewFenModel newFenModel;
    public void getFenModel(NewFenModel newFenModel){
        this.newFenModel=newFenModel;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                FenLei fenlei= (FenLei) msg.obj;
                if(newFenModel!=null){
                    newFenModel.getSuccess(fenlei);

                }

            }
        }
    };

    public void getFenLei(String s){
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
                FenLei fenLei = gson.fromJson(string, FenLei.class);
                Message message=new Message();
                message.what=0;
                message.obj=fenLei;
                handler.sendMessage(message);
            }
        });


    }
}
