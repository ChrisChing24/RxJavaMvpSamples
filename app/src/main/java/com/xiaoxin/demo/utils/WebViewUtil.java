package com.xiaoxin.demo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by yuanlili on 2017/4/13.
 */

public class WebViewUtil {


    @SuppressLint("SetJavaScriptEnabled")
    public static void setupWebView2(WebView webView) {
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setUseWideViewPort(true); //自适应手机屏幕:设置webview推荐使用的窗口
        settings.setLoadWithOverviewMode(true); //自适应手机屏幕:设置webview加载的页面的模式
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH); //提高渲染的优先级
        //settings.setBlockNetworkImage(true); //把图片加载放在最后来加载渲染
        webView.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }


    @SuppressLint("SetJavaScriptEnabled")
    public static void setupWebView(WebView webView, final Context context, final AVLoadingIndicatorView loadingIndicatorView) {
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setUseWideViewPort(true); //自适应手机屏幕:设置webview推荐使用的窗口
        settings.setLoadWithOverviewMode(true); //自适应手机屏幕:设置webview加载的页面的模式
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH); //提高渲染的优先级
        //settings.setBlockNetworkImage(true); //把图片加载放在最后来加载渲染
        webView.setBackgroundColor(Color.argb(0, 0, 0, 0));


        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                loadingIndicatorView.setVisibility(View.VISIBLE);
                loadingIndicatorView.show();
//                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                loadingIndicatorView.hide();
//                super.onPageFinished(view, url);
            }
        });
    }
}
