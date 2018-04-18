package com.bw.jdxiangmu.wode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.wode.bean.TJInFo;


import java.util.List;

/**
 * Created by lenovo on 2018/3/8.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {
    Context context;
    List<TJInFo.TuijianBean.ListBean> list;

    public MyAdapter(Context context, List<TJInFo.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
        MyViewHodler myViewHodler=new MyViewHodler(inflate);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        TJInFo.TuijianBean.ListBean listBean = list.get(position);
        String images = listBean.getImages();
        String[] split = images.split("\\|");

        Glide.with(context).load(split[0]).into(holder.imageView);

        String title = listBean.getTitle();

        holder.textView.setText(title);

    }

    @Override
    public int getItemCount() {
        return list.size();
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
}
