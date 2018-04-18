package com.bw.jdxiangmu.shouye.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.bean.ShangPinInfo;

import java.util.List;

/**
 * Created by lenovo on 2018/3/12.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {
    Context context;
    List<ShangPinInfo.DataBean> data  ;
    private View inflate;

    public MyAdapter(Context context, List<ShangPinInfo.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.sousuolishijilu, parent, false);
        MyViewHodler myViewHodler=new MyViewHodler(inflate);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        ShangPinInfo.DataBean dataBean = data.get(position);
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(holder.imageView);
        String title = dataBean.getTitle();
        holder.textView.setText(title);
        double price = dataBean.getPrice();
        String subhead = dataBean.getSubhead();
        String createtime = dataBean.getCreatetime();
        holder.price.setText(price+"");
        holder.date.setText(createtime);
        holder.subhead.setText(subhead);
        final int pid = dataBean.getPid();
        final String detailUrl = dataBean.getDetailUrl();
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setUrl!=null){
                    setUrl.OnUrl(pid);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

  public   class MyViewHodler extends RecyclerView.ViewHolder{

         public final TextView textView,price,date,subhead;
         public final ImageView imageView;


        public MyViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.lsjl);
            price = (TextView) itemView.findViewById(R.id.price);
            date = (TextView) itemView.findViewById(R.id.date);
            subhead = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.img);


        }
    }
    public interface setUrl{
        void OnUrl(int pid);
    }
    setUrl setUrl;
    public void getUrl(setUrl setUrl){
        this.setUrl=setUrl;
    }
}
