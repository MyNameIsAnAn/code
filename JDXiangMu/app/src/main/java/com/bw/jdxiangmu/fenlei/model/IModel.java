package com.bw.jdxiangmu.fenlei.model;


import android.util.Log;

import com.bw.jdxiangmu.fenlei.api.Api;
import com.bw.jdxiangmu.fenlei.app.AppService;
import com.bw.jdxiangmu.fenlei.bean.ZiFenLeiInfo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/3/21.
 */

public class IModel {


    public interface setNewModel{

        void getSuccess(ZiFenLeiInfo ziFenLeiInfo);
    }

    setNewModel setNewModel;
    public void setOnListenerModel(setNewModel setNewModel){

        this.setNewModel=setNewModel;
    }

        public void getIModel(String i){

            Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.ZIFENLEI_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            AppService appService = retrofit.create(AppService.class);
            final Observable<ZiFenLeiInfo> zifenlei = appService.getZifenlei(i);
            zifenlei.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ZiFenLeiInfo>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(ZiFenLeiInfo ziFenLeiInfo) {
                        if(setNewModel!=null){
                            setNewModel.getSuccess(ziFenLeiInfo);
                            Log.d("AAAgetIModel",ziFenLeiInfo.getMsg());
                        }
                        }
                    });
        }

}
