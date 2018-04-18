package com.bw.jdxiangmu.shouye.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.SouSuoShangPinXiangQingActivity;
import com.bw.jdxiangmu.shouye.bean.GoodsInfo;

import java.util.List;

/**
 * Created by lenovo on 2018/4/2.
 */

public class GoodsErJiLieBiao extends BaseExpandableListAdapter {
    Context context;
    List<GoodsInfo.DataBean> data;

    public GoodsErJiLieBiao(Context context, List<GoodsInfo.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view=View.inflate(context, R.layout.shouyeerjiliebaiitem1,null);
       TextView textView= (TextView) view.findViewById(R.id.text);
        String name = data.get(i).getName();
        textView.setText(name);
        Log.d("BBB",name);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        view=View.inflate(context,R.layout.erjiliebaiitem2,null);
       RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.rlv);
        List<GoodsInfo.DataBean.ListBean> list = data.get(i).getList();
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        GoodsAdapter goodsAdapter=new GoodsAdapter(context,list);
        recyclerView.setAdapter(goodsAdapter);
        goodsAdapter.getUrl(new GoodsAdapter.setUrl() {
            @Override
            public void OnUrl(String name) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("name",name);
                intent.putExtras(bundle);
                intent.setClass(context, SouSuoShangPinXiangQingActivity.class);
                context.startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
