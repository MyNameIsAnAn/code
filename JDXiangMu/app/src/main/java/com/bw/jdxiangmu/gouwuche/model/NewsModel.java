package com.bw.jdxiangmu.gouwuche.model;

import com.bw.jdxiangmu.gouwuche.bean.DatasBean;
import com.bw.jdxiangmu.gouwuche.bean.MessageBean;
import com.bw.jdxiangmu.gouwuche.presenter.NewsPresenter;
import com.bw.jdxiangmu.gouwuche.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;


/**
 * Created by lenovo on 2018/4/9.
 */

public class NewsModel implements IModel{
    private NewsPresenter presenter;

    public NewsModel(NewsPresenter presenter){
        this.presenter = (NewsPresenter) presenter;

    }
    @Override
    public void getData(String uid,String pid) {
        Flowable<MessageBean<List<DatasBean>>> flowable = RetrofitUtils.getInstance().getApiService().getDatas(uid);
        presenter.getNews(flowable);

    }
}