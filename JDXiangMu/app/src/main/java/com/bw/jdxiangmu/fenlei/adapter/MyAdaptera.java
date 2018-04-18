package com.bw.jdxiangmu.fenlei.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.fenlei.bean.ErJiLieBiao;
import com.bw.jdxiangmu.fenlei.bean.ImgText;

import java.util.List;

/**
 * Created by lenovo on 2018/3/9.
 */

public class MyAdaptera extends RecyclerView.Adapter<MyAdaptera.MyVIewHodler>{
    Context context;
    List<ErJiLieBiao.DataBean.ListBean> list;
    private View inflate;

    public MyAdaptera(Context context, List<ErJiLieBiao.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
        public MyVIewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.erjiliebaiitem3, parent, false);
        MyVIewHodler myVIewHodler=new MyVIewHodler(inflate);
        return myVIewHodler;
        }

        @Override
        public void onBindViewHolder(MyVIewHodler holder, int position) {
            ErJiLieBiao.DataBean.ListBean listBean = list.get(position);
            final String name = listBean.getName();
            String icon = listBean.getIcon();
            Glide.with(context).load(icon).into(holder.imageView);
            holder.textView.setText(name);
            final int pscid = listBean.getPscid();

            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(setCid!=null){
                        setCid.OnSuccess(name);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyVIewHodler extends  RecyclerView.ViewHolder{

            public final ImageView imageView;
            public final TextView textView;

            public MyVIewHodler(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imgg);
                textView = (TextView) itemView.findViewById(R.id.textt);
            }
        }

    public interface setCid{

        void OnSuccess(String name);
    }
    setCid setCid;
    public void getCidListener(setCid setCid){
        this.setCid=setCid;
    }

}


