package com.bw.jdxiangmu.gouwuche.api;

import com.bw.jdxiangmu.gouwuche.bean.DatasBean;
import com.bw.jdxiangmu.gouwuche.bean.MessageBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface ApiService {
    @GET("product/getCarts")
    Flowable<MessageBean<List<DatasBean>>> getDatas(@Query("uid") String uid);
    @GET("product/deleteCart")
    Flowable<MessageBean> deleteData(@Query("uid") String uid, @Query("pid") String pid);
}
