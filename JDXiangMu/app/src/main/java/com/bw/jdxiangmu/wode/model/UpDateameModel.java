package com.bw.jdxiangmu.wode.model;

import com.bw.jdxiangmu.wode.api.Api;
import com.bw.jdxiangmu.wode.app.AppService;
import com.bw.jdxiangmu.wode.bean.UpDateNameInfo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/3/29.
 */

public class UpDateameModel {

    public interface NewModel{
        void OnSuccess(String string);
        void OnError(String s);
    }
    NewModel newModel;
    public void getNewModel(NewModel newModel){
        this.newModel=newModel;
    }

        public void getModel(String id,String token,String name){

            Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.URL_UPDATENAME)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            AppService appService = retrofit.create(AppService.class);
            Observable<UpDateNameInfo> name1 = appService.getName(id, token, name);
            name1.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UpDateNameInfo>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(UpDateNameInfo upDateNameInfo) {
                            String code = upDateNameInfo.getCode();
                            String msg = upDateNameInfo.getMsg();
                            if(code.equals("0")){
                                newModel.OnSuccess(msg);

                            }else {
                                newModel.OnError(msg);
                            }
                        }
                    });



        }


}





