package com.bw.jdxiangmu.shouye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.fenlei.activity.BaseActivity;
import com.bw.jdxiangmu.shouye.activity.bean.PidInfo;
import com.bw.jdxiangmu.shouye.activity.presenter.IPresenter2;
import com.bw.jdxiangmu.shouye.activity.view.IView2;
import com.bw.jdxiangmu.utils.SPUtil;
import com.bw.jdxiangmu.wode.activity.LoginActivity;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PidXiangQingActivity extends BaseActivity<IPresenter2> implements IView2 {


    private WebView webView;
    private String pid;
    private String addcarurl = "https://www.zhaoapi.cn/product/addCart";
    @Override
    protected void getData() {

        Bundle extras = getIntent().getExtras();
        pid = extras.getString("pid");
        presenter.getPresenter("android", pid);
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.webview);

    }

    @Override
    protected IPresenter2 getPresenter() {
        presenter = new IPresenter2(this);
        return presenter;
    }

    @Override
    protected int getID() {
        return R.layout.activity_pid_xiang_qing;
    }


    @Override
    public void OnSuccess(PidInfo shangPinInfo) {
        PidInfo.DataBean data = shangPinInfo.getData();
        final double price = data.getPrice();
        String  url = data.getDetailUrl();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            //调用自身浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            //当页面加载完毕时  给H5的加入购物车按钮添加点击事件
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //给加入购物车按钮添加点击事件
                setWebImageClick();
                //给立即购买添加点击事件
                setWebImageClickLjgm();
            }
        });
        webView.loadUrl(url);

        SPUtil spUtil=new SPUtil(PidXiangQingActivity.this,"pddl");
        final boolean flag = spUtil.getBoolean("flag", false);
        final String string = spUtil.getString("uid", null);

            //java回调js代码，不要忘了@JavascriptInterface这个注解，不然点击事件不起作用
            webView.addJavascriptInterface(new JsCallJavaObj() {
                @JavascriptInterface
                @Override
                public void showBigImg() {
                    if(flag) {
                        Toast.makeText(PidXiangQingActivity.this, "你点击了加入购物车", Toast.LENGTH_SHORT).show();
                        //添加购物车
                        addCar(string, pid);
                    }else{
                        Toast.makeText(PidXiangQingActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PidXiangQingActivity.this, LoginActivity.class));
                    }
                }
            },"jsCallJavaObj");


        webView.addJavascriptInterface(new JsCallJavaLjgm() {
            @JavascriptInterface
            @Override
            public void showBigImgLjgm() {
                Toast.makeText(PidXiangQingActivity.this, "你点击了立即购买", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("uid",string);
                bundle.putString("price",price+"");
                intent.putExtras(bundle);
                intent.setClass(PidXiangQingActivity.this,DingDanActivity.class);
                startActivity(intent);

            }
        },"JsCallJavaLjgm");

    }
    private interface JsCallJavaObj{
        void showBigImg();

    }
    private interface JsCallJavaLjgm{
        void showBigImgLjgm();
    }
    private  void setWebImageClick() {
        String jsCode="javascript:(function(){" +
                "var btn=document.getElementById(\"addCartBtm\");" +
                "btn.onclick=function(){" +
                "window.jsCallJavaObj.showBigImg();" +
                "}})()";
        webView.loadUrl(jsCode);
    }
    private  void setWebImageClickLjgm() {
        String jsCode="javascript:(function(){" +
                "var btn=document.getElementById(\"directorderBtm\");" +
                "btn.onclick=function(){" +
                "window.JsCallJavaLjgm.showBigImgLjgm();" +
                "}})()";
        webView.loadUrl(jsCode);
    }

    public void addCar(String uid,String pid){
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(addcarurl+"?uid="+uid+"&pid="+pid+"&source=android").build();
        build.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
