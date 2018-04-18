package com.bw.jdxiangmu.gouwuche.model;

import com.bw.jdxiangmu.gouwuche.bean.MessageBean;
import com.bw.jdxiangmu.gouwuche.presenter.DelPresenter;
import com.bw.jdxiangmu.gouwuche.utils.RetrofitUtils;

import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/4/9.
 */

public class DelModel implements IModel {
    private DelPresenter presenter;

    public DelModel(DelPresenter presenter){
        this.presenter =  presenter;

    }
    @Override
    public void getData(String uid,String pid) {

        Flowable<MessageBean> delFlowable = RetrofitUtils.getInstance().getApiService().deleteData(uid,pid);
        presenter.delData(delFlowable);
    }
}
