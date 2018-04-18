package com.bw.jdxiangmu.shouye.activity.app;

import com.bw.jdxiangmu.shouye.activity.bean.DDLBiao;
import com.bw.jdxiangmu.shouye.activity.bean.DingDanInfo;
import com.bw.jdxiangmu.shouye.activity.bean.PidInfo;
import com.bw.jdxiangmu.shouye.activity.bean.ShangPinInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2018/3/21.
 */

public interface AppService {

    @POST("searchProducts")
    @FormUrlEncoded
    Observable<ShangPinInfo> getShangPin(@Field("source") String source, @Field("keywords") String keywords, @Field("page") int page);
    @GET("getProductDetail")
    Observable<PidInfo> getPid(@Query("pid") String pid);
    @GET("createOrder")
    Observable<DingDanInfo> getDingDanInfo(@Query("uid") String pid,@Query("price") String price);
    @GET("getOrders")
    Observable<DDLBiao> getDDLBiao(@Query("uid") String uid,@Query("page") int page);


}
