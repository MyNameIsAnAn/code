package com.bw.jdxiangmu.faxian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2018/4/14.
 */

public abstract class BaseFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(getFargementLayout(),container,false);
        ininView(view);
        return view;
    }



    protected abstract void ininView(View view);

    public abstract int getFargementLayout();

}