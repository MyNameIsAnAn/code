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

public class PidAdapter extends RecyclerView.Adapter<PidAdapter.MyViewHodler> {
    Context context;
    List<ShangPinInfo.DataBean> data  ;
    private View inflate;

    public PidAdapter(Context context, List<ShangPinInfo.DataBean> data) {
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
        final String detailUrl = dataBean.getDetailUrl();
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setUrl!=null){
                    setUrl.OnUrl(detailUrl);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

  public   class MyViewHodler extends RecyclerView.ViewHolder{

         public final TextView textView;
         public final ImageView imageView;


        public MyViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.lsjl);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }
    public interface setUrl{
        void OnUrl(String detailUrl);
    }
   setUrl setUrl;
    public void getUrl(setUrl setUrl){
        this.setUrl=setUrl;
    }
}
