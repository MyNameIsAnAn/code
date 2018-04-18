package com.bw.jdxiangmu.gouwuche.presenter;

import com.bw.jdxiangmu.gouwuche.bean.DatasBean;
import com.bw.jdxiangmu.gouwuche.bean.MessageBean;
import com.bw.jdxiangmu.gouwuche.model.NewsModel;
import com.bw.jdxiangmu.gouwuche.view.Iview;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;


/**
 * Created by lenovo on 2018/4/9.
 */

public class NewsPresenter implements BasePresenter {
    private Iview iv;
    private DisposableSubscriber subscriber1;

    public void attachView(Iview iv) {
        this.iv = iv;
    }

    public void detachView() {
        if (iv != null) {
            iv = null;
        }
        if (!subscriber1.isDisposed()){
            subscriber1.dispose();
        }

    }

    @Override
    public void getData(String uid,String pid) {
        NewsModel model = new NewsModel(this);
        model.getData(uid,pid);
    }

    public void getNews(Flowable<MessageBean<List<DatasBean>>> flowable) {
        subscriber1 = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean<List<DatasBean>>>() {
                    @Override
                    public void onNext(MessageBean<List<DatasBean>> listMessageBean) {
                        if (listMessageBean != null) {
                            List<DatasBean> list = listMessageBean.getData();
                            if (list != null) {
                                iv.onSuccess(list);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        iv.onFailed((Exception) t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}