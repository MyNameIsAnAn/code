package com.bw.jdxiangmu.shouye.activity.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bw.jdxiangmu.shouye.activity.bean.PidInfo;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/3/21.
 */

public class IModel2 {


    public interface setModel{
        void OnSuccess(PidInfo shangPinInfo);

    }
    setModel setModel;
    public void setOnModelListener(setModel setModel){
        this.setModel=setModel;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                PidInfo pidInfo= (PidInfo) msg.obj;
                if(setModel!=null){
                    setModel.OnSuccess(pidInfo);
                }

            }
        }
    };

    public void getModel(String a, String s){



       /* Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.PIDXINXI_URL)

                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AppService appService = retrofit.create(AppService.class);
        Observable<PidInfo> pid = appService.getPid(s);
        pid.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PidInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PidInfo shangPinInfo) {
                        if(setModel!=null){
                            setModel.OnSuccess(shangPinInfo);

                        }
                        String code = shangPinInfo.getCode();
                        Log.d("PIDDD",code);
                    }
                });*/

        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url("https://www.zhaoapi.cn/product/getProductDetail?source="+a+"&pid="+s).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                PidInfo pidInfo = gson.fromJson(string, PidInfo.class);
                Message message=new Message();
                message.what=0;
                message.obj=pidInfo;
                handler.sendMessage(message);
                String code = pidInfo.getCode();
                Log.d("PIDDD",code);
            }
        });



    }


}
