package com.bw.jdxiangmu.fenlei.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.fenlei.adapter.IAdapter;
import com.bw.jdxiangmu.fenlei.bean.ZiFenLeiInfo;
import com.bw.jdxiangmu.fenlei.presenter.IPresenter;
import com.bw.jdxiangmu.fenlei.view.IView;
import com.bw.jdxiangmu.shouye.activity.PidXiangQingActivity;

import java.util.List;

public class ZiFenLeiXiangQingActivity extends BaseActivity<IPresenter> implements IView {


    private RecyclerView mRlv;

    @Override
    protected void getData() {
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("id");
        presenter.getIPresenter(string);
    }

    @Override
    protected void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected IPresenter getPresenter() {
        presenter = new IPresenter(this);
        return presenter;
    }

    @Override
    protected int getID() {
        return R.layout.activity_zi_fen_lei_xiang_qing;
    }

    @Override
    public void OnSuccess(ZiFenLeiInfo ziFenLeiInfo) {

        List<ZiFenLeiInfo.DataBean> data = ziFenLeiInfo.getData();
        IAdapter iAdapter=new IAdapter(ZiFenLeiXiangQingActivity.this,data);
        mRlv.setAdapter(iAdapter);
        iAdapter.getCidListener(new IAdapter.setUrl() {
            @Override
            public void OnSuccess(int id) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("url",id+"");
                intent.putExtras(bundle);
                intent.setClass(ZiFenLeiXiangQingActivity.this, PidXiangQingActivity.class);
                startActivity(intent);
            }
        });
    }


}
