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

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHodler> {
    Context context;
    List<JingDong.MiaoshaBean.ListBeanX> list ;
    private View inflate;

    public MyAdapter2(Context context, List<JingDong.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
        MyViewHodler myViewHodler=new MyViewHodler(inflate);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        final JingDong.MiaoshaBean.ListBeanX listBeanX = list.get(position);
        String images = listBeanX.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(holder.imageView);
        String title = listBeanX.getTitle();
        double price = listBeanX.getPrice();
        holder.textView.setText("价格:"+price);
        holder.textView1.setText(title);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pid = listBeanX.getPid();
                if(setUrl!=null){
                    setUrl.OnUrl(pid);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

  public   class MyViewHodler extends RecyclerView.ViewHolder{

        public final TextView textView,textView1;
        public final ImageView imageView;

        public MyViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text2);
            textView1 = (TextView) itemView.findViewById(R.id.text1);
            imageView = (ImageView) itemView.findViewById(R.id.img2);

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
