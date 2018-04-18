package com.bw.jdxiangmu.faxian.api;

import com.bw.jdxiangmu.faxian.bean.FaxianBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lenovo on 2018/4/14.
 */

public interface ApiService {
    @GET("index?type=top&key=205706b7d07479f45345e17e7052aecd")
    Observable<FaxianBean> getshowFaxian();
}
