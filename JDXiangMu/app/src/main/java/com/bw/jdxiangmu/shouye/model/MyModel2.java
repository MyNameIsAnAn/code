package com.bw.jdxiangmu.shouye.model;

import android.os.Handler;
import android.os.Message;

import com.bw.jdxiangmu.shouye.bean.Jgg;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/3/8.
 */

public class MyModel2 {

    public interface NewModel{

        void SuccessModel(Jgg jgg);
    }
    NewModel newModel;
    public void getNewModel(NewModel newModel){

        this.newModel=newModel;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                Jgg jgg= (Jgg) msg.obj;
                if(newModel!=null){

                    newModel.SuccessModel(jgg);

                }

            }
        }
    };

    public void getSyModel(String url){

        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Gson gson=new Gson();
                Jgg jgg = gson.fromJson(string, Jgg.class);
                Message message=new Message();
                message.what=0;
                message.obj=jgg;
                handler.sendMessage(message);
            }
        });


    }

}
