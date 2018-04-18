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
import com.bw.jdxiangmu.shouye.bean.GoodsInfo;

import java.util.List;

/**
 * Created by lenovo on 2018/3/12.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHodler> {
    Context context;
    List<GoodsInfo.DataBean.ListBean> list  ;
    private View inflate;

    public GoodsAdapter(Context context, List<GoodsInfo.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.shouyeerjiliebaiitem3, parent, false);
        MyViewHodler myViewHodler=new MyViewHodler(inflate);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        GoodsInfo.DataBean.ListBean listBean = list.get(position);
        String icon = listBean.getIcon();
        Glide.with(context).load(icon).into(holder.imageView);
        final String name = listBean.getName();

        holder.textView.setText(name);

        final int pscid = listBean.getPscid();
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(setUrl!=null){
                    setUrl.OnUrl(name);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

  public   class MyViewHodler extends RecyclerView.ViewHolder{

        public final TextView textView;
        public final ImageView imageView;

        public MyViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textt);
            imageView = (ImageView) itemView.findViewById(R.id.imgg);

        }
    }

    public interface setUrl{
        void OnUrl(String name);
    }
   setUrl setUrl;
    public void getUrl(setUrl setUrl){
        this.setUrl=setUrl;
    }

}
