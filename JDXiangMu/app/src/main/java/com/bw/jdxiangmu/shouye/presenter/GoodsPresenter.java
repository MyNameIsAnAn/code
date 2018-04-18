package com.bw.jdxiangmu.shouye.presenter;

import com.bw.jdxiangmu.shouye.bean.GoodsInfo;
import com.bw.jdxiangmu.shouye.model.GoodsModel;
import com.bw.jdxiangmu.shouye.view.GoodsView;

/**
 * Created by lenovo on 2018/4/2.
 */

public class GoodsPresenter {
        GoodsModel goodsModel;
    GoodsView goodsView;

    public GoodsPresenter(GoodsView goodsView) {
        this.goodsView = goodsView;
        goodsModel=new GoodsModel();
    }
    public void getPresenter(String s){
        goodsModel.getModel(s);
        goodsModel.getNewModel(new GoodsModel.NewModel() {
            @Override
            public void OnSuccess(GoodsInfo jingDong) {
                goodsView.OnSuccess(jingDong);
            }
        });

    }
}
