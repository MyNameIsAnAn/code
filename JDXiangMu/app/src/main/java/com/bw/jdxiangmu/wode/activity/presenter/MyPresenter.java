package com.bw.jdxiangmu.wode.activity.presenter;


import com.bw.jdxiangmu.wode.activity.bean.RegInFo;
import com.bw.jdxiangmu.wode.activity.model.MyModel;
import com.bw.jdxiangmu.wode.activity.view.MyView;


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
    public void getPresenter(String s1,String s2){
        myModel.getModel(s1,s2);
        myModel.getNewModel(new MyModel.NewModel() {
            @Override
            public void OnSuccess(RegInFo jingDong) {
                myView.OnSuccess(jingDong);
            }

            @Override
            public void OnErros(String tjInFo) {
                myView.OnErros(tjInFo);
            }
        });
    }
}
