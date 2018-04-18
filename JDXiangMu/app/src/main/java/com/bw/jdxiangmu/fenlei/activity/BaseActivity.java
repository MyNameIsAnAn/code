package com.bw.jdxiangmu.fenlei.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.jdxiangmu.R;

/**
 * Created by lenovo on 2018/3/21.
 */

public abstract class BaseActivity<P> extends AppCompatActivity {
    public P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getID());
        presenter=getPresenter();
        initView();
        getData();

    }

    protected abstract void getData();
    protected abstract void initView();
    protected abstract P getPresenter();
    protected abstract int getID();

}
