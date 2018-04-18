package com.bw.jdxiangmu.shouye.activity.utilks;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/3/21.
 */

public class OkHttp implements Interceptor {


    private static final String TAG = "MyInter";
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原来的body
        Request request = chain.request();
        RequestBody body = request.body();
        if (body instanceof FormBody) {
            //遍历原来的所有参数，加到新的Body里面，最后将公共参数加到新的Body
            FormBody.Builder newBuilder = new FormBody.Builder();
            for (int i = 0; i < ((FormBody) body).size(); i++) {
                String name = ((FormBody) body).name(i);
                String value = ((FormBody) body).value(i);
                //放入新的
                newBuilder.add(name, value);
            }
            //在将公共参数添加进去
            newBuilder.add("source", "android");
            FormBody newBody = newBuilder.build();
            //创建新的请求
            Request newRequest = request.newBuilder().post(newBody).build();
            Response response = chain.proceed(newRequest);
            return response;
        }
        return null;
    }
}
