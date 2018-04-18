package com.bw.jdxiangmu.wode.activity.presenter;


import com.bw.jdxiangmu.wode.activity.bean.LoginInFo;
import com.bw.jdxiangmu.wode.activity.bean.RegInFo;
import com.bw.jdxiangmu.wode.activity.model.MyModel;
import com.bw.jdxiangmu.wode.activity.model.MyModel2;
import com.bw.jdxiangmu.wode.activity.view.MyView;
import com.bw.jdxiangmu.wode.activity.view.MyView2;


/**
 * Created by lenovo on 2018/3/12.
 */

public class MyPresenter2 {
    MyModel2 myModel2;
    MyView2 myView;

    public MyPresenter2(MyView2 myView) {
        this.myView = myView;
        myModel2=new MyModel2();
    }
    public void getPresenter(String s1,String s2){
        myModel2.getModel(s1,s2);
        myModel2.getNewModel(new MyModel2.NewModel() {
            @Override
            public void OnSuccess(LoginInFo jingDong) {
                myView.OnSuccess(jingDong);
            }

            @Override
            public void OnErros(String tjInFo) {
                myView.OnErros(tjInFo);
            }
        });
    }
}
