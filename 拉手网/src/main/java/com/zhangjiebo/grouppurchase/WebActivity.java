package com.zhangjiebo.grouppurchase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String webUrl = intent.getStringExtra("WebUrl");
        WebView webView_lashou = (WebView) findViewById(R.id.webView_lashou);
        webView_lashou.setWebViewClient(new WebViewClient());
        WebSettings settings = webView_lashou.getSettings();
        settings.setJavaScriptEnabled(true);
        webView_lashou.loadUrl(webUrl);
    }

}
