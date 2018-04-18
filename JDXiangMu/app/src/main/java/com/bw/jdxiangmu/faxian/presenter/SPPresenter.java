package com.bw.jdxiangmu.faxian.presenter;

import android.util.Log;

import com.bw.jdxiangmu.faxian.bean.SPInfo;
import com.bw.jdxiangmu.faxian.model.SPModel;
import com.bw.jdxiangmu.faxian.view.SPView;

/**
 * Created by lenovo on 2018/4/10.
 */

public class SPPresenter {
    SPModel spModel;
    SPView spView;

    public SPPresenter(SPView spView) {
        this.spView = spView;
        spModel=new SPModel();
    }

    public void getPresenter(){
       // spModel.getModel();
        spModel.getOnListener(new SPModel.OnListener() {
            @Override
            public void OnSuccess(SPInfo spInfo) {
                spView.OnSuccess(spInfo);
            }
        });
        Log.d("SP","getPresenter");
    }
}
