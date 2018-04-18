package com.bw.jdxiangmu.shouye.activity.model;

import com.bw.jdxiangmu.shouye.activity.api.Api;
import com.bw.jdxiangmu.shouye.activity.app.AppService;
import com.bw.jdxiangmu.shouye.activity.bean.ShangPinInfo;
import com.bw.jdxiangmu.shouye.activity.utilks.OkHttp;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/3/21.
 */

public class IModel {


    public interface setModel{
        void OnSuccess(ShangPinInfo shangPinInfo);

    }
    setModel setModel;
    public void setOnModelListener(setModel setModel){
        this.setModel=setModel;
    }

    public void getModel(String a,String k,int page){
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new OkHttp()).build();


        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.XINXI_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AppService appService = retrofit.create(AppService.class);
        Observable<ShangPinInfo> shangPin = appService.getShangPin(a, k, page);
        shangPin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShangPinInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShangPinInfo shangPinInfo) {
                        if(setModel!=null){
                            setModel.OnSuccess(shangPinInfo);

                        }
                    }
                });
    }


}
