package com.bw.jdxiangmu.faxian.presenter;

import com.bw.jdxiangmu.faxian.BasePresenter;
import com.bw.jdxiangmu.faxian.bean.FaxianBean;
import com.bw.jdxiangmu.faxian.model.FaxianModel;
import com.bw.jdxiangmu.faxian.view.FaxianView;

/**
 * Created by lenovo on 2018/4/14.
 */

public class FaxianPrensenter extends BasePresenter<FaxianView> {


    public FaxianPrensenter(FaxianView iview) {
        super(iview);
    }

    public void faxianP(){


        new FaxianModel().faxianM(new FaxianModel.OnResponseCallBack() {
            @Override
            public void ResponseSuccess(FaxianBean faxianBean) {
                if (Iview!=null){
                    Iview.SucessCallBack(faxianBean);
                }
            }
        });
    }
}