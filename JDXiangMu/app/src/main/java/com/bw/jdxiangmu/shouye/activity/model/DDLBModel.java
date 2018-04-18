package com.bw.jdxiangmu.shouye.activity.model;

import android.util.Log;

import com.bw.jdxiangmu.shouye.activity.api.Api;
import com.bw.jdxiangmu.shouye.activity.app.AppService;
import com.bw.jdxiangmu.shouye.activity.bean.DDLBiao;

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

public class DDLBModel {
    public interface setDDLBListener{
        void OnSuccess(DDLBiao ddlBiao);
    }
    setDDLBListener setDDLBListener;
    public void setOnModelListener( setDDLBListener setDDLBListener){
        this.setDDLBListener=setDDLBListener;
    }

        public void getModel(String uid,int page){


            Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.DINGDAN_URL)

                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AppService appService = retrofit.create(AppService.class);
            Observable<DDLBiao> ddlBiao = appService.getDDLBiao(uid,page);
            ddlBiao.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DDLBiao>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(DDLBiao ddlBiao) {
                        if(setDDLBListener!=null){
                            setDDLBListener.OnSuccess(ddlBiao);

                        }
                        }
                    });
            Log.d("AAAA","model");
        }


}
