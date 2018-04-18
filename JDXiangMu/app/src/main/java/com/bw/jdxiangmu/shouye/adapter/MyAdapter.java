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
import com.bw.jdxiangmu.shouye.bean.JingDong;


import java.util.List;

/**
 * Created by lenovo on 2018/3/12.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {
    Context context;
    List<JingDong.TuijianBean.ListBean> list1 ;
    private View inflate;

    public MyAdapter(Context context, List<JingDong.TuijianBean.ListBean> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
        MyViewHodler myViewHodler=new MyViewHodler(inflate);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        final JingDong.TuijianBean.ListBean listBean = list1.get(position);
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(holder.imageView);
        double bargainPrice = listBean.getBargainPrice();
        holder.textView.setText(bargainPrice+"");

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

  public   class MyViewHodler extends RecyclerView.ViewHolder{

        public final TextView textView;
        public final ImageView imageView;

        public MyViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.img2);

        }
    }

    public interface setUrl{
        void OnUrl(int detailUrl);
    }
   setUrl setUrl;
    public void getUrl(setUrl setUrl){
        this.setUrl=setUrl;
    }

}
