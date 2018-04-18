package com.bw.jdxiangmu.wode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.wode.adapter.MyAdapter;
import com.bw.jdxiangmu.wode.bean.TJInFo;
import com.bw.jdxiangmu.wode.presenter.MyPresenter;
import com.bw.jdxiangmu.wode.view.MyView;

import java.util.List;

/**
 * Created by lenovo on 2018/3/15.
 */

public class F1 extends Fragment implements MyView{
    private View view;
    private RecyclerView mRlv;
    String string="https://www.zhaoapi.cn/ad/getAd";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wdf1, container, false);
        initView(view);
        mRlv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        MyPresenter myPresenter=new MyPresenter(this);
        myPresenter.getPresenter(string);
        return view;
    }

    private void initView(View view) {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
    }

    @Override
    public void OnSuccess(TJInFo jingDong) {
        TJInFo.TuijianBean tuijian = jingDong.getTuijian();
        List<TJInFo.TuijianBean.ListBean> list = tuijian.getList();
        MyAdapter myAdapter=new MyAdapter(getActivity(),list);
        mRlv.setAdapter(myAdapter);

    }
}
