package com.bw.jdxiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.fenlei.adapter.ErJiLieBiaoAdapter;
import com.bw.jdxiangmu.fenlei.adapter.FenAdapter;
import com.bw.jdxiangmu.fenlei.bean.ErJiLieBiao;
import com.bw.jdxiangmu.fenlei.bean.FenLei;
import com.bw.jdxiangmu.fenlei.presenter.FenPresenter;
import com.bw.jdxiangmu.fenlei.presenter.FenPresenter2;
import com.bw.jdxiangmu.fenlei.view.FenView;
import com.bw.jdxiangmu.fenlei.view.FenView2;
import java.util.List;

/**
 * Created by lenovo on 2018/1/31.
 */

public class Fragment3 extends Fragment implements FenView,FenView2 {

    private View view;
    private RecyclerView mRlv;
    private ExpandableListView mElist;
    String url="http://120.27.23.105/product/getCatagory";
    String url2="http://120.27.23.105/product/getProductCatagory?cid=";
    private FenPresenter2 fenPresenter2;
    private List<ErJiLieBiao.DataBean.ListBean> list1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        initView(view);
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        FenPresenter fenPresenter=new FenPresenter(this);
        fenPresenter.getFenPresenter(url);
        fenPresenter2 = new FenPresenter2(this);
        fenPresenter2.getFenPresenter(url2+1);
        return view;
    }
    private void initView(View view) {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        mElist = (ExpandableListView) view.findViewById(R.id.elist);
    }

    @Override
    public void OnSuccess(FenLei fenLei) {
        final List<FenLei.DataBean> data = fenLei.getData();

        final FenAdapter fenAdapter=new FenAdapter(getActivity(),data);
        mRlv.setAdapter(fenAdapter);
        fenAdapter.getDanJi(new FenAdapter.DanJi() {
            @Override
            public void OnSuccess1(int cid) {
                FenLei.DataBean dataBean = data.get(cid);
                int cid1 = dataBean.getCid();
                fenAdapter.setThisPosition(cid);
                fenPresenter2.getFenPresenter(url2+cid1);
                fenAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void OnSuccess(ErJiLieBiao erJiLieBiao) {
        List<ErJiLieBiao.DataBean> data = erJiLieBiao.getData();
        mElist.setAdapter(new ErJiLieBiaoAdapter(getActivity(),data));
        int count = mElist.getCount();
        for (int i=0; i<count; i++) {
            mElist.expandGroup(i);
        }
        for (int i = 0; i <data.size() ; i++) {
            ErJiLieBiao.DataBean dataBean = data.get(i);
            list1 = dataBean.getList();

        }
    }
}
