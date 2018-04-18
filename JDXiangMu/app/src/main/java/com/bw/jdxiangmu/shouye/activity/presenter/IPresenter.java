package com.bw.jdxiangmu.shouye.activity.presenter;

import com.bw.jdxiangmu.shouye.activity.bean.ShangPinInfo;
import com.bw.jdxiangmu.shouye.activity.model.IModel;
import com.bw.jdxiangmu.shouye.activity.view.IView;

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

    public void getPresenter(String a,String k,int page){

        iModel.getModel(a,k,page);
        iModel.setOnModelListener(new IModel.setModel() {
            @Override
            public void OnSuccess(ShangPinInfo shangPinInfo) {
                iView.OnSuccess(shangPinInfo);
            }
        });
    }
}
