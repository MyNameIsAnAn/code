package com.bw.jdxiangmu.faxian;

/**
 * Created by lenovo on 2018/4/14.
 */

public class BasePresenter<V extends BaseView> {

    protected V Iview;

    public BasePresenter(V iview) {
        Iview = iview;
    }
    public void onDestroy(){

        Iview=null;
    }


}
