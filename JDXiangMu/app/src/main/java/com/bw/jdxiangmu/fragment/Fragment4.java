package com.bw.jdxiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.gouwuche.adapter.MyAdapter;
import com.bw.jdxiangmu.gouwuche.bean.DatasBean;
import com.bw.jdxiangmu.gouwuche.bean.MessageBean;
import com.bw.jdxiangmu.gouwuche.bean.SomeId;
import com.bw.jdxiangmu.gouwuche.event.MessageEvent;
import com.bw.jdxiangmu.gouwuche.event.PriceAndCountEvent;
import com.bw.jdxiangmu.gouwuche.presenter.DelPresenter;
import com.bw.jdxiangmu.gouwuche.presenter.NewsPresenter;
import com.bw.jdxiangmu.gouwuche.view.Iview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/1/31.
 */

public class Fragment4 extends Fragment implements Iview {


    private View view;
    private ExpandableListView mElv;
    private CheckBox mCheckbox2;
    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private String uid = "12584";
    private NewsPresenter presenter;
    private MyAdapter adapter;
    private List<DatasBean> groupList;
    private List<List<DatasBean.ListBean>> childList;
    private String pid;
    private DelPresenter delPresenter;
    private View view1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment4, container, false);
        initView(view1);
        return view1;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new NewsPresenter();
        presenter.attachView(this);
        delPresenter = new DelPresenter();
        delPresenter.attachView(this);
        presenter.getData(uid,pid);

        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });
    }


    @Override
    public void delSuccess(MessageBean listMessageBean) {
        Toast.makeText(getActivity(),listMessageBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Object o) {
        groupList = new ArrayList<>();
        childList = new ArrayList<>();
        if(o!=null){
            List<DatasBean> list = (List<DatasBean> )o;
            if(list!=null){
                groupList.addAll(list);
                for (int i = 0; i < list.size(); i++) {
                    List<DatasBean.ListBean> datas = list.get(i).getList();
                    childList.add(datas);
                }
                adapter = new MyAdapter(getActivity(), groupList, childList);
                mElv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //默认checkbox选中
                mCheckbox2.setChecked(true);
                adapter.changeAllListCbState(true);
                mElv.setGroupIndicator(null);
                for (int i=0;i<groupList.size();i++){
                    mElv.expandGroup(i);
                }

            }
        }
    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText("￥"+event.getPrice() );
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SomeId event) {
        pid = event.getPid();
        Log.e("zxz","我得到了pid:"+pid);
        delPresenter.getData(uid,pid);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private void initView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
    }
}
