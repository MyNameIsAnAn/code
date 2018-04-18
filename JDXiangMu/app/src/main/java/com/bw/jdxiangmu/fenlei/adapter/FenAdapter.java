package com.bw.jdxiangmu.fenlei.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.fenlei.bean.FenLei;

import java.util.List;

/**
 * Created by lenovo on 2018/3/9.
 */

public class FenAdapter extends RecyclerView.Adapter<FenAdapter.FenViewHodler> {
    Context context;
    List<FenLei.DataBean> data;
    private View inflate;

    public FenAdapter(Context context, List<FenLei.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    //先声明一个int成员变量
    private int thisPosition;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    @Override
    public FenViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.fenleiitem1, parent, false);
        FenViewHodler fenViewHodler = new FenViewHodler(inflate);
        return fenViewHodler;
    }

    @Override
    public void onBindViewHolder(final FenViewHodler holder, final int position) {
        FenLei.DataBean dataBean = data.get(position);
        String name = dataBean.getName();
        holder.textView.setText(name);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (danJi != null) {
                    danJi.OnSuccess1(holder.getLayoutPosition());
                }
            }
        });

        if (position == getthisPosition()) {
            holder.textView.setTextColor(Color.RED);
        } else {
            holder.textView.setTextColor(Color.BLACK);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FenViewHodler extends RecyclerView.ViewHolder {

        public final TextView textView;

        public FenViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.fltext);
        }
    }

    public interface DanJi {
        void OnSuccess1(int cid);
    }
    DanJi danJi;

    public void getDanJi(DanJi danJi) {
        this.danJi = danJi;
    }




}
