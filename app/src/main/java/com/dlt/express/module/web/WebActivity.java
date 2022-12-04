package com.dlt.express.module.web;

import android.os.Bundle;
import android.webkit.WebSettings;

import com.dlt.express.R;
import com.dlt.express.base.AppActivity;
import com.hzlh.sdk.util.YLog;
import com.hzlh.sdk.view.YWebView;

/**
 * 描述：网页activity
 * 日期: 2022/12/3 14:55
 *
 * @author Zhout
 */
public class WebActivity extends AppActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");

        initHeader(title);

        YWebView webView = findViewById(R.id.webView);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setTextZoom(450);
        webView.loadUrl(url == null ? "" : url);
        YLog.d(" url = " + url);
    }
}
