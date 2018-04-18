package com.bw.jdxiangmu.shouye.presenter;


import com.bw.jdxiangmu.shouye.bean.Jgg;
import com.bw.jdxiangmu.shouye.model.MyModel2;
import com.bw.jdxiangmu.shouye.view.MyView2;

/**
 * Created by lenovo on 2018/3/8.
 */

public class MyPresenter2 {
    MyModel2 syModel2;
    MyView2 syView2;

    public MyPresenter2(MyView2 syView2) {

        this.syView2 = syView2;
        syModel2=new MyModel2();
    }

    public void getSyPresenter(String url){

        syModel2.getSyModel(url);
        syModel2.getNewModel(new MyModel2.NewModel() {
            @Override
            public void SuccessModel(Jgg jgg) {
                syView2.OnSuccess(jgg);
            }
        });
    }
}
