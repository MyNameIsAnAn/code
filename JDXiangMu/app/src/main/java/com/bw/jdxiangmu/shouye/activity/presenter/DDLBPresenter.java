package com.bw.jdxiangmu.shouye.activity.presenter;

import android.util.Log;

import com.bw.jdxiangmu.shouye.activity.bean.DDLBiao;
import com.bw.jdxiangmu.shouye.activity.model.DDLBModel;
import com.bw.jdxiangmu.shouye.activity.view.DDLBView;

/**
 * Created by lenovo on 2018/4/12.
 */

public class DDLBPresenter {
    DDLBModel ddlbModel;
    DDLBView ddlbView;

    public DDLBPresenter(DDLBView ddlbView) {
        this.ddlbView = ddlbView;
        ddlbModel=new DDLBModel();
    }
    public void getPresenter(String uid,int page){
        ddlbModel.getModel(uid,page);
        ddlbModel.setOnModelListener(new DDLBModel.setDDLBListener() {
            @Override
            public void OnSuccess(DDLBiao ddlBiao) {
                ddlbView.OnSuccess(ddlBiao);
            }
        });
        Log.d("AAAA","presenter");
    }
}
