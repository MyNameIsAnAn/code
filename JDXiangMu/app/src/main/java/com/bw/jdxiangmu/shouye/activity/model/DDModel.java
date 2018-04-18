package com.bw.jdxiangmu.shouye.activity.model;

import android.util.Log;

import com.bw.jdxiangmu.shouye.activity.api.Api;
import com.bw.jdxiangmu.shouye.activity.app.AppService;
import com.bw.jdxiangmu.shouye.activity.bean.DingDanInfo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/4/12.
 */

public class DDModel {
    public interface setDDListener{
        void OnSuccess(String msg);
        void OnError(String msg);

    }
    setDDListener setDDListener;
    public void setOnModelListener(setDDListener setDDListener){
        this.setDDListener=setDDListener;
    }

        public void getModel(String pid,String price){


            Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.DINGDAN_URL)

                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AppService appService = retrofit.create(AppService.class);
            Observable<DingDanInfo> dingDanInfo = appService.getDingDanInfo(pid, price);
                dingDanInfo.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DingDanInfo>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(DingDanInfo dingDanInfo) {
                                String code = dingDanInfo.getCode();
                                String msg = dingDanInfo.getMsg();
                                if(code.equals("0")){
                                    if(setDDListener!=null){
                                        setDDListener.OnSuccess(msg);

                                    }
                                }else {
                                    if(setDDListener!=null){
                                        setDDListener.OnError(msg);

                                    }
                                }
                            }
                        });
            Log.d("AAAA","model");
        }


}
