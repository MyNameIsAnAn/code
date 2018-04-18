package com.bw.jdxiangmu.faxian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bw.jdxiangmu.R;

public class Faxian_Webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faxian__webview);
        WebView viewById = (WebView) findViewById(R.id.faxian_wvb);
        WebSettings settings = viewById.getSettings();
        settings.setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String faxianurl = intent.getStringExtra("faxianurl");
        viewById.loadUrl(faxianurl);
    }
}
