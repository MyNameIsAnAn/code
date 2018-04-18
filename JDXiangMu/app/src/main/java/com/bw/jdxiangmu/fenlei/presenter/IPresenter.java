package com.bw.jdxiangmu.fenlei.presenter;

import android.util.Log;

import com.bw.jdxiangmu.fenlei.bean.ZiFenLeiInfo;
import com.bw.jdxiangmu.fenlei.model.IModel;
import com.bw.jdxiangmu.fenlei.view.IView;

/**
 * Created by lenovo on 2018/3/21.
 */

public class IPresenter {

    IModel iModel;
    IView iView;

    public IPresenter(IView iView) {
        this.iView = iView;
        iModel=new IModel();
    }

    public void getIPresenter(String i){
        iModel.getIModel(i);
        iModel.setOnListenerModel(new IModel.setNewModel() {
            @Override
            public void getSuccess(ZiFenLeiInfo ziFenLeiInfo) {
                iView.OnSuccess(ziFenLeiInfo);
                Log.d("AAAgetIPresenter",ziFenLeiInfo.getMsg());
            }
        });
    }
}
