package com.bw.jdxiangmu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("url");
        mWeb.loadUrl(string);
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
    }
}
