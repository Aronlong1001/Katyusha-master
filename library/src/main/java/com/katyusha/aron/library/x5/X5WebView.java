package com.katyusha.aron.library.x5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by jixiaolong on 2017/11/29.
 */

public class X5WebView extends WebView {

    private WebViewClient client = new WebViewClient() {

        /**
         * 防止加载网页时调起系统浏览器
         * @param webView
         * @param url
         * @return
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setWebViewClient(client);
        initWebViewSettings();
        this.getView().setClickable(true);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提。
        webSetting.setBuiltInZoomControls(true);//设置内置的缩放控件。若为false，则该WebView不可缩放
        webSetting.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);//缩放至屏幕的大小
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    public X5WebView(Context context) {
        super(context);
        setBackgroundColor(85621);
    }
}
