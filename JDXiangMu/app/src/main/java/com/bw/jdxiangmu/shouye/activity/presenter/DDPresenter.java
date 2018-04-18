package com.bw.jdxiangmu.shouye.activity.presenter;

import android.util.Log;

import com.bw.jdxiangmu.shouye.activity.model.DDModel;
import com.bw.jdxiangmu.shouye.activity.view.DDView;

/**
 * Created by lenovo on 2018/4/12.
 */

public class DDPresenter {

    DDModel ddModel;
    DDView ddView;

    public DDPresenter(DDView ddView) {
        this.ddView = ddView;
        ddModel=new DDModel();
    }
    public void getPresenter(String pid,String price){
        ddModel.getModel(pid,price);
        ddModel.setOnModelListener(new DDModel.setDDListener() {
            @Override
            public void OnSuccess(String msg) {
                ddView.OnSuccess(msg);
            }

            @Override
            public void OnError(String msg) {
            ddView.OnError(msg);
            }
        });
        Log.d("AAAA","presenter");
    }
}
