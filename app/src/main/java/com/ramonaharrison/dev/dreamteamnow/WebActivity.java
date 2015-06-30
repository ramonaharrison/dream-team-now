package com.ramonaharrison.dev.dreamteamnow;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class WebActivity extends Activity {

    private WebView myWebView;
    private String url;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //TODO: Add progressbar for loading webpages?
        setupProgressBar();
        getUrl();
        setupWebView();
    }

    public void getUrl(){
        url = getIntent().getExtras().getString("url");
        if(url == null){
            url = "www.google.com";
        }
        setupWebView();
    }

    public void setupProgressBar(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
    }

    public void setupWebView(){
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new myWebClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()){
            myWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public class myWebClient extends WebViewClient{


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }


}
