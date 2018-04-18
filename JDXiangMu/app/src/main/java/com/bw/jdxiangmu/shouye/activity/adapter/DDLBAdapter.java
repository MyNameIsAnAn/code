package com.bw.jdxiangmu.shouye.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.bean.DDLBiao;

import java.util.List;

/**
 * Created by lenovo on 2018/4/12.
 */

public class DDLBAdapter extends RecyclerView.Adapter<DDLBAdapter.DDLBViewHolder>{
    Context context;
    List<DDLBiao.DataBean> data;
    private View inflate;

    public DDLBAdapter(Context context, List<DDLBiao.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public DDLBViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.dingdanitem, parent, false);
        DDLBViewHolder ddlbViewHolder=new DDLBViewHolder(inflate);
        return ddlbViewHolder;
    }

    @Override
    public void onBindViewHolder(final DDLBViewHolder holder, int position) {
        DDLBiao.DataBean dataBean = data.get(position);
        String title = dataBean.getTitle();
        double price = dataBean.getPrice();
        String createtime = dataBean.getCreatetime();
        holder.textView.setText(title);
        holder.textView3.setText(createtime);
        holder.textView4.setText(price+"");
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onButtonListener!=null){
                    onButtonListener.OnSuccess(holder.getLayoutPosition());

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DDLBViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final TextView textView3;
        private final TextView textView4;
        private final Button button;

        public DDLBViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView3 = (TextView) itemView.findViewById(R.id.textView3);
            textView4 = (TextView) itemView.findViewById(R.id.textView4);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }
    public interface OnButtonListener{
        void OnSuccess(int layoutPosition);
    }
    OnButtonListener onButtonListener;
    public void setOnButtonListener(OnButtonListener onButtonListener){
        this.onButtonListener=onButtonListener;
    }

}
