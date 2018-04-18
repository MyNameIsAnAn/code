package com.bw.jdxiangmu.wode.presenter;

import android.content.DialogInterface;

import com.bw.jdxiangmu.wode.model.UpDateameModel;
import com.bw.jdxiangmu.wode.view.UpDateView;

/**
 * Created by lenovo on 2018/4/2.
 */

public class UpDatePresenter {

    UpDateameModel upDateameModel;
    UpDateView upDateView;

    public UpDatePresenter(UpDateView upDateView) {
        this.upDateView = upDateView;
        upDateameModel=new UpDateameModel();
    }



    public void getNamePresenter(String id,String token,String name){

        upDateameModel.getModel(id,token,name);
        upDateameModel.getNewModel(new UpDateameModel.NewModel() {
            @Override
            public void OnSuccess(String string) {
                upDateView.OnSuccess(string);
            }

            @Override
            public void OnError(String s) {
            upDateView.OnError(s);
            }
        });

    }
}
