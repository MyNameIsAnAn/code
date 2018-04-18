package com.bw.jdxiangmu.shouye.app;

import com.bw.jdxiangmu.shouye.bean.GoodsInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2018/4/2.
 */

public interface AppService {
    @GET("getProductCatagory")
    Observable<GoodsInfo> getGoods(@Query("cid") String cid);

}
