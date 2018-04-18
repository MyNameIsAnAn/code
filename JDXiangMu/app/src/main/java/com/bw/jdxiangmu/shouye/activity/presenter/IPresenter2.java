package com.bw.jdxiangmu.shouye.activity.presenter;

import android.util.Log;

import com.bw.jdxiangmu.shouye.activity.bean.PidInfo;
import com.bw.jdxiangmu.shouye.activity.model.IModel2;
import com.bw.jdxiangmu.shouye.activity.view.IView2;

/**
 * Created by lenovo on 2018/3/21.
 */

public class IPresenter2 {

    IModel2 iModel;

    IView2 iView;

    public IPresenter2(IView2 iView) {
        this.iView = iView;
        iModel=new IModel2();
    }

    public void getPresenter(String a,String s){

        iModel.getModel(a,s);
        iModel.setOnModelListener(new IModel2.setModel() {
            @Override
            public void OnSuccess(PidInfo shangPinInfo) {
                iView.OnSuccess(shangPinInfo);

            }
        });
        Log.d("PIDDD","getPresenter");
    }
}
