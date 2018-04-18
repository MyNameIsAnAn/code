package com.bw.jdxiangmu.wode.activity.model;

import android.os.Handler;
import android.os.Message;

import com.bw.jdxiangmu.wode.activity.api.Api;
import com.bw.jdxiangmu.wode.activity.bean.LoginInFo;
import com.bw.jdxiangmu.wode.activity.bean.RegInFo;
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

public class MyModel2 {

    public interface NewModel{
        void OnSuccess(LoginInFo tjInFo);
        void OnErros(String tjInFo);
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
                LoginInFo tjInFo= (LoginInFo) msg.obj;
                String code = tjInFo.getCode();
                if(code.equals("1")){
                    newModel.OnErros(tjInFo.getMsg());
                }else{
                    if(newModel!=null){
                        newModel.OnSuccess(tjInFo);

                    }
                }
            }
        }
    };
    public void getModel(String s1,String s2){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(Api.url2+"?mobile="+ s1 +"&password="+ s2).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                LoginInFo regInFo = gson.fromJson(string, LoginInFo.class);
                Message message=new Message();
                message.what=0;
                message.obj=regInFo;
                handler.sendMessage(message);
            }
        });
    }
}
