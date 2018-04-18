package com.bw.jdxiangmu.fenlei.app;

import com.bw.jdxiangmu.fenlei.bean.ZiFenLeiInfo;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2018/3/21.
 */

public interface AppService {
@GET("getProducts")
Observable<ZiFenLeiInfo> getZifenlei(@Query("pscid") String pscid);
}
