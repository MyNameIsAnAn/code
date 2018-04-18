package com.bw.jdxiangmu.shouye.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.bean.Jgg;

import java.util.List;

/**
 * Created by lenovo on 2018/3/8.
 */

public class  MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHodler> {
    Context context;
    List<Jgg.DataBean> data;
    private View inflate;

    public MyAdapter3(Context context, List<Jgg.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item4, parent, false);
        MyViewHodler myViewHodler=new MyViewHodler(inflate);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        Jgg.DataBean dataBean = data.get(position);
        String icon = dataBean.getIcon();
        Glide.with(context).load(icon).into(holder.imageView);
        String name = dataBean.getName();
        holder.textView.setText(name);
        final int cid = dataBean.getCid();
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setListener!=null){
                    setListener.OnSuccess(cid);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder{

        public final ImageView imageView;
        public final TextView textView;

        public MyViewHodler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img2);
            textView = (TextView) itemView.findViewById(R.id.text2);
        }
    }
    public interface setListener{
        void OnSuccess(int cid);
    }
    setListener setListener;
    public void getListener(setListener setListener){
        this.setListener=setListener;
    }


}
