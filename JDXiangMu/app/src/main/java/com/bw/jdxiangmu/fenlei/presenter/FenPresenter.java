package com.bw.jdxiangmu.fenlei.presenter;

import com.bw.jdxiangmu.fenlei.bean.FenLei;
import com.bw.jdxiangmu.fenlei.model.FenModel;
import com.bw.jdxiangmu.fenlei.view.FenView;

/**
 * Created by lenovo on 2018/3/9.
 */

public class FenPresenter {
    FenModel fenModel;
    FenView fenView;

    public FenPresenter(FenView fenView) {
        this.fenView = fenView;
        fenModel=new FenModel();
    }

    public void getFenPresenter(String s){
        fenModel.getFenLei(s);
        fenModel.getFenModel(new FenModel.NewFenModel() {
            @Override
            public void getSuccess(FenLei fenlei) {
                fenView.OnSuccess(fenlei);
            }
        });
    }
}
