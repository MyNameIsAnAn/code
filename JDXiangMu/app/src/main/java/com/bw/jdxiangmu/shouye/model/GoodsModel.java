package com.bw.jdxiangmu.shouye.model;

import com.bw.jdxiangmu.shouye.api.Api;
import com.bw.jdxiangmu.shouye.app.AppService;
import com.bw.jdxiangmu.shouye.bean.GoodsInfo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/3/12.
 */

public class GoodsModel {

    public interface NewModel{
        void OnSuccess(GoodsInfo jingDong);
    }
    NewModel newModel;
    public void getNewModel(NewModel newModel){
        this.newModel=newModel;
    }


    public void getModel(String s){

        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.URL___)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        AppService appService = retrofit.create(AppService.class);
        Observable<GoodsInfo> goods = appService.getGoods(s);
        goods.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GoodsInfo goodsInfo) {
                        if(newModel!=null){
                            newModel.OnSuccess(goodsInfo);

                        }
                    }
                });


    }
}
