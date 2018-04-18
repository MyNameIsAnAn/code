package com.bw.jdxiangmu.shouye;

import android.content.Intent;
import android.util.Log;
import android.widget.ExpandableListView;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.fenlei.activity.BaseActivity;
import com.bw.jdxiangmu.shouye.adapter.GoodsErJiLieBiao;
import com.bw.jdxiangmu.shouye.bean.GoodsInfo;
import com.bw.jdxiangmu.shouye.presenter.GoodsPresenter;
import com.bw.jdxiangmu.shouye.view.GoodsView;

import java.util.List;

public class ShouYeFenLeiActivity extends BaseActivity<GoodsPresenter> implements GoodsView{


    private ExpandableListView expandableListView;

    @Override
    protected void getData() {
        Intent intent = getIntent();
        String cid = intent.getStringExtra("cid");
        Log.d("AAAA",cid+"");
        presenter.getPresenter(cid);
    }

    @Override
    protected void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.erji);

    }

    @Override
    protected GoodsPresenter getPresenter() {
        presenter=new GoodsPresenter(this);
        return presenter;
    }

    @Override
    protected int getID() {
        return R.layout.activity_shou_ye_fen_lei;
    }

    @Override
    public void OnSuccess(GoodsInfo goodsInfo) {
        List<GoodsInfo.DataBean> data = goodsInfo.getData();
        String name = data.get(0).getName();
        Log.d("BBB",name);
        GoodsErJiLieBiao goodsErJiLieBiao=new GoodsErJiLieBiao(this,data);
        expandableListView.setAdapter(goodsErJiLieBiao);
        int count = expandableListView.getCount();
        for (int i=0; i<count; i++) {
            expandableListView.expandGroup(i);
        }
    }
}
