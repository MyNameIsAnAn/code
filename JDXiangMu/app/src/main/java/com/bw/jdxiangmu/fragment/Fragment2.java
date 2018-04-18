package com.bw.jdxiangmu.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.faxian.BaseFragment;
import com.bw.jdxiangmu.faxian.Faxian_Webview;
import com.bw.jdxiangmu.faxian.adapter.MyAdapter;
import com.bw.jdxiangmu.faxian.bean.FaxianBean;
import com.bw.jdxiangmu.faxian.presenter.FaxianPrensenter;
import com.bw.jdxiangmu.faxian.view.FaxianView;

import java.util.List;

/**
 * Created by lenovo on 2018/1/31.
 */

public class Fragment2 extends BaseFragment implements FaxianView {


    private RecyclerView rl;
    private List<FaxianBean.ResultBean.DataBean> data;


    @Override
    protected void ininView(View view) {

        rl = (RecyclerView) view.findViewById(R.id.rl_faxian);
        rl.setLayoutManager(new LinearLayoutManager(getActivity()));
        FaxianPrensenter faxianPrensenter = new FaxianPrensenter(this);
        faxianPrensenter.faxianP();
    }

    @Override
    public int getFargementLayout() {
        return R.layout.fragment2;
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void SucessCallBack(FaxianBean faxianBean) {

        data = faxianBean.getResult().getData();
        MyAdapter myAdapter = new MyAdapter(data,getActivity());
        rl.setAdapter(myAdapter);
        myAdapter.setOnItemListenner(new MyAdapter.setItemListenner() {
            @Override
            public void setClickListenner(int position) {
                String url = data.get(position).getUrl();
                Intent intent = new Intent(getActivity(), Faxian_Webview.class);
                intent.putExtra("faxianurl",url);
                startActivity(intent);
            }
        });

    }
}
