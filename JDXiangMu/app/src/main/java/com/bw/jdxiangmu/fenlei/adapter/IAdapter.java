package com.bw.jdxiangmu.fenlei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.fenlei.bean.ZiFenLeiInfo;

import java.util.List;

/**
 * Created by lenovo on 2018/3/21.
 */

public class IAdapter extends RecyclerView.Adapter<IAdapter.IViewHolder>{
    Context context;
    List<ZiFenLeiInfo.DataBean> data;
    private View inflate;

    public IAdapter(Context context, List<ZiFenLeiInfo.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.zifenleixinagqing, parent, false);
        IViewHolder iViewHolder=new IViewHolder(inflate);
        return iViewHolder;
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        ZiFenLeiInfo.DataBean dataBean = data.get(position);
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(holder.imageView);
        String title = dataBean.getTitle();
        holder.textView.setText(title);
        final String detailUrl = dataBean.getDetailUrl();
        final int pid = dataBean.getPid();
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setUrl!=null){
                    setUrl.OnSuccess(pid);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class IViewHolder extends RecyclerView.ViewHolder{

        public final TextView textView;
        public final ImageView imageView;

        public IViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text2);
            imageView = (ImageView) itemView.findViewById(R.id.img2);
        }
    }

    public interface setUrl{

        void OnSuccess(int pid);
    }
   setUrl setUrl;
    public void getCidListener(setUrl setUrl){
        this.setUrl=setUrl;
    }


}
