package com.bw.jdxiangmu.shouye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.adapter.MyAdapter;
import com.bw.jdxiangmu.shouye.activity.bean.ShangPinInfo;
import com.bw.jdxiangmu.shouye.activity.presenter.IPresenter;
import com.bw.jdxiangmu.shouye.activity.view.IView;

import java.util.ArrayList;
import java.util.List;

public class SouSuoShangPinXiangQingActivity extends AppCompatActivity implements IView {

    private ImageView mI;
    private EditText mJ;
    private TextView mQuxiao;
    private RecyclerView mRlv;
    private SwipyRefreshLayout mSrl;
    int page=1;
    Handler handler;
    private IPresenter iPresenter;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo_shang_pin_xiang_qing);
        initView();
        handler=new Handler();
        mSrl.setColorSchemeResources(R.color.colorAccent);
        mSrl.setDirection(SwipyRefreshLayoutDirection.BOTH);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        Bundle extras = getIntent().getExtras();
        final String name2 = extras.getString("name");

        mJ.setText(name2);
        iPresenter = new IPresenter(this);
        iPresenter.getPresenter("android",name2,page);
        mI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSrl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                page=1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iPresenter.getPresenter("android",name2,page);
                        mSrl.setRefreshing(false);
                        Toast.makeText(SouSuoShangPinXiangQingActivity.this,"刷新了",Toast.LENGTH_LONG).show();
                    }
                },2000);
            }

            @Override
            public void onLoad(int index) {
                page++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iPresenter.getPresenter("android",name2,page);
                        mSrl.setRefreshing(false);
                        Toast.makeText(SouSuoShangPinXiangQingActivity.this,"加载了",Toast.LENGTH_LONG).show();
                    }
                },2000);
            }
        });


        mQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ed = mJ.getText().toString();
                Toast.makeText(SouSuoShangPinXiangQingActivity.this,"点击",Toast.LENGTH_LONG).show();
                iPresenter.getPresenter("android",ed,page);
                myAdapter.notifyDataSetChanged();
            }
        });


    }
    List<ShangPinInfo.DataBean> data2;
    @Override
    public void OnSuccess(ShangPinInfo shangPinInfo) {
        List<ShangPinInfo.DataBean> data = shangPinInfo.getData();
        if(page==1){
            data2=new ArrayList<>();
        }
        data2.addAll(data);
        myAdapter = new MyAdapter(this,data2);
        mRlv.setAdapter(myAdapter);
        myAdapter.getUrl(new MyAdapter.setUrl() {
            @Override
            public void OnUrl(int pid) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("pid",pid+"");
                intent.putExtras(bundle);
                intent.setClass(SouSuoShangPinXiangQingActivity.this, PidXiangQingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mI = (ImageView) findViewById(R.id.i);
        mJ = (EditText) findViewById(R.id.j);
        mQuxiao = (TextView) findViewById(R.id.quxiao);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mSrl = (SwipyRefreshLayout) findViewById(R.id.srl);

    }
}
