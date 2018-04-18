package com.bw.jdxiangmu.gouwuche.view;

import com.bw.jdxiangmu.gouwuche.bean.MessageBean;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface Iview {
    void onSuccess(Object o);
    void onFailed(Exception e);
    void delSuccess(MessageBean listMessageBean);
}
