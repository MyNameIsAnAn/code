package com.bw.jdxiangmu.wode.app;

import com.bw.jdxiangmu.wode.bean.UpDateNameInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2018/4/2.
 */

public interface AppService {

    @GET("updateNickName")
    Observable<UpDateNameInfo> getName(@Query("uid") String uid,@Query("token") String token,@Query("nickname") String name);

}
