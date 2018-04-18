package com.bw.jdxiangmu.faxian.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.faxian.bean.FaxianBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2018/4/14.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<FaxianBean.ResultBean.DataBean> list;
    Context context;

    public MyAdapter(List<FaxianBean.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==2){
            view = LayoutInflater.from(context).inflate(R.layout.faxian_item3,parent,false);
            final MyViewHolder3 myViewHolder3 = new MyViewHolder3(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = myViewHolder3.getLayoutPosition();
                    listenner.setClickListenner(layoutPosition);
                }
            });
            return myViewHolder3;
        }else if (viewType==1){
            view = LayoutInflater.from(context).inflate(R.layout.faxian_item2,parent,false);
            final MyViewHolder2 myViewHolder2 = new MyViewHolder2(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = myViewHolder2.getLayoutPosition();
                    listenner.setClickListenner(layoutPosition);
                }
            });
            return myViewHolder2;
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.faxian_item,parent,false);
            final MyViewHolder myViewHolder = new MyViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = myViewHolder.getLayoutPosition();
                    listenner.setClickListenner(layoutPosition);
                }
            });
            return  myViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String title = list.get(position).getTitle();
        String date = list.get(position).getDate();

        if (holder instanceof MyViewHolder){
            String thumbnail_pic_s = list.get(position).getThumbnail_pic_s();
            Uri uri = Uri.parse(thumbnail_pic_s);

            MyViewHolder holder1 = (MyViewHolder) holder;
            holder1.simpleDraweeView.setImageURI(uri);
            holder1.textView.setText(list.get(position).getTitle());
            holder1.textView2.setText(list.get(position).getDate());
        }else if (holder instanceof MyViewHolder2){
            String thumbnail_pic_s1 = list.get(position).getThumbnail_pic_s();
            String thumbnail_pic_s2 = list.get(position).getThumbnail_pic_s02();
            Uri uri21 = Uri.parse(thumbnail_pic_s1);
            Uri uri22 = Uri.parse(thumbnail_pic_s2);
            MyViewHolder2 holder2 = (MyViewHolder2) holder;
            holder2.simpleDraweeView.setImageURI(uri21);
            holder2.simpleDraweeView2.setImageURI(uri22);
            holder2.textView.setText(title);
            holder2.textView2.setText(date);
        }else if (holder instanceof MyViewHolder3){
            String thumbnail_pic_s3 = list.get(position).getThumbnail_pic_s();
            String thumbnail_pic_s32 = list.get(position).getThumbnail_pic_s02();
            String thumbnail_pic_s33 = list.get(position).getThumbnail_pic_s03();
            Uri uri3 = Uri.parse(thumbnail_pic_s3);
            Uri uri32 = Uri.parse(thumbnail_pic_s32);
            Uri uri33 = Uri.parse(thumbnail_pic_s33);
            MyViewHolder3 holder3 = (MyViewHolder3) holder;
            holder3.simpleDraweeView.setImageURI(uri3);
            holder3.simpleDraweeView2.setImageURI(uri32);
            holder3.simpleDraweeView3.setImageURI(uri33);
            holder3.textView.setText(title);
            holder3.textView2.setText(date);
        }

    }


    @Override
    public int getItemViewType(int position) {
        String thumbnail_pic_s = list.get(position).getThumbnail_pic_s();
        String thumbnail_pic_s2 = list.get(position).getThumbnail_pic_s02();
        String thumbnail_pic_s3 = list.get(position).getThumbnail_pic_s03();

        if (thumbnail_pic_s!=null&&thumbnail_pic_s2!=null&&thumbnail_pic_s3!=null){

            return 2;
        }else if (thumbnail_pic_s!=null&&thumbnail_pic_s2!=null){

            return 1;
        }else{
            return 0;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textView,textView2;
        public MyViewHolder(View itemView) {
            super(itemView);

            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdv_iv);
            textView = (TextView) itemView.findViewById(R.id.tv_titile);
            textView2 = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView,simpleDraweeView2;
        TextView textView,textView2;
        public MyViewHolder2(View itemView) {
            super(itemView);

            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdv_iv2);
            simpleDraweeView2 = (SimpleDraweeView) itemView.findViewById(R.id.sdv_iv22);
            textView = (TextView) itemView.findViewById(R.id.tv_titile2);
            textView2 = (TextView) itemView.findViewById(R.id.tv_date2);
        }
    }
    public class MyViewHolder3 extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView,simpleDraweeView2,simpleDraweeView3;
        TextView textView,textView2;
        public MyViewHolder3(View itemView) {
            super(itemView);

            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdv_iv3);
            simpleDraweeView2 = (SimpleDraweeView) itemView.findViewById(R.id.sdv_iv31);
            simpleDraweeView3 = (SimpleDraweeView) itemView.findViewById(R.id.sdv_iv32);
            textView = (TextView) itemView.findViewById(R.id.tv_titile3);
            textView2 = (TextView) itemView.findViewById(R.id.tv_date3);
        }
    }

    public interface setItemListenner{
        void setClickListenner(int position);
    }
    setItemListenner listenner;
    public void setOnItemListenner(setItemListenner listenner1){
        listenner  = listenner1;
    }
}
