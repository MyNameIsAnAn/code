package com.bw.jdxiangmu.faxian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.faxian.bean.SPInfo;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
/**
 * Created by lenovo on 2018/4/10.
 */

public class SPAdapter extends RecyclerView.Adapter<SPAdapter.SPViewHolder> {
    Context context;
    List<SPInfo.DataBeanX.DataBean> data;

    public SPAdapter(Context context, List<SPInfo.DataBeanX.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public SPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.videoitem, parent, false);
        SPViewHolder spViewHolder=new SPViewHolder(inflate);

        return spViewHolder;
    }

    @Override
    public void onBindViewHolder(SPViewHolder holder, int position) {

       /* boolean setUp1 = holder.videoView.setUp(data.get(position).getGroup().getMp4_url(),JCVideoPlayer.SCREEN_LAYOUT_LIST, data.get(position).getGroup().getContent());
        if(setUp1){
            Glide.with(context).load(data.get(position).getGroup().getLarge_cover().getUrl_list().get(0).getUrl()).into(holder.videoView.thumbImageView);
        }*/


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SPViewHolder extends RecyclerView.ViewHolder{

        public final JCVideoPlayerStandard videoView;

        public SPViewHolder(View itemView) {
            super(itemView);
            videoView = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoviewwww);
        }
    }
}
