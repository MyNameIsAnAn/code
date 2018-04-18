package com.bw.jdxiangmu.shouye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.adapter.DDLBAdapter;
import com.bw.jdxiangmu.shouye.activity.bean.DDLBiao;
import com.bw.jdxiangmu.shouye.activity.presenter.DDLBPresenter;
import com.bw.jdxiangmu.shouye.activity.presenter.DDPresenter;
import com.bw.jdxiangmu.shouye.activity.view.DDLBView;
import com.bw.jdxiangmu.shouye.activity.view.DDView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingDanActivity extends AppCompatActivity implements DDView, DDLBView {

    @BindView(R.id.tvAll)
    TextView mTvAll;
    @BindView(R.id.tvWait)
    TextView mTvWait;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.srl)
    SwipyRefreshLayout mSrl;
    private String uid;
    int page = 1;
    Handler handler;
    private DDLBPresenter ddlbPresenter;
    List<DDLBiao.DataBean> data2 ;
    private String price;
    private DDPresenter ddPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        ButterKnife.bind(this);
        uid = getIntent().getStringExtra("uid");
        price = getIntent().getStringExtra("price");

            Log.d("AAAA","price");
            ddPresenter = new DDPresenter(this);
            ddPresenter.getPresenter(uid, price);

            ddlbPresenter = new DDLBPresenter(this);
            ddlbPresenter.getPresenter(uid, page);
            Log.d("AAAA","null");


        handler=new Handler();
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mSrl.setColorSchemeColors(R.color.colorAccent);
        mSrl.setDirection(SwipyRefreshLayoutDirection.BOTH);
        mSrl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                page=1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DingDanActivity.this,"刷新了订单",Toast.LENGTH_LONG).show();
                        ddlbPresenter.getPresenter(uid,page);
                        mSrl.setRefreshing(false);
                    }
                },2000);
            }

            @Override
            public void onLoad(int index) {
                page++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DingDanActivity.this,"加载了订单",Toast.LENGTH_LONG).show();
                        ddlbPresenter.getPresenter(uid,page);
                        mSrl.setRefreshing(false);
                    }
                },2000);
            }
        });

    }

    @OnClick({R.id.tvAll, R.id.tvWait, R.id.rv, R.id.srl})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tvAll:
                break;
            case R.id.tvWait:
                break;
            case R.id.rv:
                break;
            case R.id.srl:
                break;
        }
    }

    @Override
    public void OnSuccess(String msg) {
        Toast.makeText(DingDanActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnError(String msg) {
        if(!TextUtils.isEmpty(price)){
            Toast.makeText(DingDanActivity.this, msg, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void OnSuccess(DDLBiao ddlBiao) {

        final List<DDLBiao.DataBean> data1 = ddlBiao.getData();
        Log.d("CCC", data1.size() + "");
        if(page==1){
            data2=new ArrayList<>();
        }
        data2.addAll(data1);

        DDLBAdapter ddlbAdapter = new DDLBAdapter(this, data2);
        mRv.setAdapter(ddlbAdapter);
        ddlbAdapter.setOnButtonListener(new DDLBAdapter.OnButtonListener() {
            @Override
            public void OnSuccess(int layoutPosition) {
                DDLBiao.DataBean dataBean = data1.get(layoutPosition);
                double price = dataBean.getPrice();
                String title = dataBean.getTitle();
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("price",price+"");
                bundle.putString("title",title);
                intent.putExtras(bundle);
                intent.setClass(DingDanActivity.this,ZhiFuActivity.class);
                startActivity(intent);
            }
        });
    }
}
