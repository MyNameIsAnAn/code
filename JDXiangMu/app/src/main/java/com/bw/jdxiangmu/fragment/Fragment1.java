package com.bw.jdxiangmu.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.ShouYeFenLeiActivity;
import com.bw.jdxiangmu.shouye.activity.PidXiangQingActivity;
import com.bw.jdxiangmu.shouye.adapter.MyAdapter;
import com.bw.jdxiangmu.shouye.adapter.MyAdapter2;
import com.bw.jdxiangmu.shouye.adapter.MyAdapter3;
import com.bw.jdxiangmu.shouye.app.MyImg;
import com.bw.jdxiangmu.shouye.bean.Jgg;
import com.bw.jdxiangmu.shouye.bean.JingDong;
import com.bw.jdxiangmu.shouye.fenge.FenGeXian;
import com.bw.jdxiangmu.shouye.presenter.MyPresenter;
import com.bw.jdxiangmu.shouye.presenter.MyPresenter2;
import com.bw.jdxiangmu.shouye.view.MyView;
import com.bw.jdxiangmu.shouye.view.MyView2;
import com.youth.banner.Banner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/1/31.
 */

public class Fragment1 extends Fragment implements MyView, MyView2 {


    String s = "https://www.zhaoapi.cn/ad/getAd";
    String jgg = "https://www.zhaoapi.cn/product/getCatagory";
    List<String> stringList;
    private View view;
    private Banner mBanner;

    private RecyclerView mRlv;
    private TextView mTj;
    private RecyclerView mMss;

    int miaosha = 100000000;
    /**
     * 京东秒杀:00:1:59
     */
    private TextView mMssss;
    private RecyclerView mErer;
    /**
     * 请输入搜索关键字
     */
    private EditText mJ;
    private ImageView imageView;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1, container, false);
        initView(view);

        stringList = new ArrayList<>();
        MyPresenter myPresenter = new MyPresenter(this);
        myPresenter.getPresenter(s);
        MyPresenter2 myPresenter2 = new MyPresenter2(this);
        myPresenter2.getSyPresenter(jgg);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mErer.addItemDecoration(new FenGeXian(getActivity(), FenGeXian.HORIZONTAL_LIST));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayout.HORIZONTAL, false);
        mErer.setLayoutManager(gridLayoutManager);
        mRlv.setLayoutManager(linearLayoutManager);
        mMss.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        CountDownTimer countDownTimer = new CountDownTimer(miaosha, 1000) {
            @Override
            public void onTick(long l) {
                String format = simpleDateFormat.format(new Date(l));
                mMssss.setText("京东秒杀:" + format);
            }

            @Override
            public void onFinish() {

            }
        }.start();
        //沉浸式状态栏
       /* if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/


        return view;

    }

    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        mMss = (RecyclerView) view.findViewById(R.id.mss);
        mMssss = (TextView) view.findViewById(R.id.mssss);
        mErer = (RecyclerView) view.findViewById(R.id.erer);
    }

    @Override
    public void OnSuccess(JingDong jingDong) {
        List<JingDong.DataBean> data = jingDong.getData();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            stringList.add(icon);
        }
        mBanner.setImages(stringList);
        mBanner.setDelayTime(1000);
        mBanner.setImageLoader(new MyImg());
        mBanner.start();
        JingDong.MiaoshaBean miaosha = jingDong.getMiaosha();
        List<JingDong.MiaoshaBean.ListBeanX> list = miaosha.getList();
        MyAdapter2 myAdapter2 = new MyAdapter2(getActivity(), list);
        mMss.setAdapter(myAdapter2);
        myAdapter2.getUrl(new MyAdapter2.setUrl() {
            @Override
            public void OnUrl(int pid) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("pid", pid+"");
                intent.putExtras(bundle);
                intent.setClass(getActivity(), PidXiangQingActivity.class);
                startActivity(intent);
            }
        });

        JingDong.TuijianBean tuijian = jingDong.getTuijian();
        List<JingDong.TuijianBean.ListBean> list1 = tuijian.getList();
        MyAdapter myAdapter = new MyAdapter(getActivity(), list1);
        mRlv.setAdapter(myAdapter);


    }

    @Override
    public void OnSuccess(Jgg shouYe) {

        List<Jgg.DataBean> data = shouYe.getData();
        Log.d("xx", data.size() + "");
        MyAdapter3 myAdapter3 = new MyAdapter3(getActivity(), data);
        mErer.setAdapter(myAdapter3);
        myAdapter3.getListener(new MyAdapter3.setListener() {
            @Override
            public void OnSuccess(int cid) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("cid", cid + "");
                intent.putExtras(bundle);
                intent.setClass(getActivity(), ShouYeFenLeiActivity.class);
                startActivity(intent);
            }
        });
    }
}
