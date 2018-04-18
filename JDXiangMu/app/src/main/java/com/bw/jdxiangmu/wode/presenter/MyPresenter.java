package com.bw.jdxiangmu.wode.presenter;


import com.bw.jdxiangmu.wode.bean.TJInFo;
import com.bw.jdxiangmu.wode.model.MyModel;
import com.bw.jdxiangmu.wode.view.MyView;

/**
 * Created by lenovo on 2018/3/12.
 */

public class MyPresenter {
    MyModel myModel;
    MyView myView;

    public MyPresenter(MyView myView) {
        this.myView = myView;
        myModel=new MyModel();
    }
    public void getPresenter(String s){
        myModel.getModel(s);
        myModel.getNewModel(new MyModel.NewModel() {
            @Override
            public void OnSuccess(TJInFo jingDong) {
                myView.OnSuccess(jingDong);
            }
        });
    }
}
