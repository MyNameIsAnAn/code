package com.bw.jdxiangmu.shouye.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.bean.SouSuoInfo;

import java.util.List;


/**
 * Created by lenovo on 2018/3/12.
 */

public class MyAdapterSouSuo extends RecyclerView.Adapter<MyAdapterSouSuo.MyViewHodler> {
    Context context;
    List<SouSuoInfo> list;
    private View inflate;

    public MyAdapterSouSuo(Context context, List<SouSuoInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.sousuo, parent, false);
        MyViewHodler myViewHodler=new MyViewHodler(inflate);


        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        SouSuoInfo souSuoInfo = list.get(position);
        final String name = souSuoInfo.getName();
        holder.textView.setText(name);
        inflate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if(setItem!=null){
                 setItem.OnItem(name);

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

        public MyViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView8);

        }
    }
    public interface setItem{
        void OnItem(String item);
    }
    setItem setItem;
    public void getUrl(setItem setItem){
        this.setItem=setItem;
    }
}
