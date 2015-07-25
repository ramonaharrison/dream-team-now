package com.ramonaharrison.dev.dreamteamnow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by c4q-anthonyf on 7/24/15.
 */
public class WebFragment extends Fragment {

    private WebView myWebView;
    private String url;
    private ProgressBar progressBar;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_web,container,false);

        //TODO: Add progressbar for loading webpages?
        setupProgressBar();
        getUrl();
        setupWebView();

        return rootView;
    }

    public void getUrl(){
        url = this.getArguments().getString("url");
        if(url == null){
            url = "www.google.com";
        }
        setupWebView();
    }

    public void setupProgressBar(){
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setMax(100);
    }

    public void setupWebView(){
        myWebView = (WebView) rootView.findViewById(R.id.webview);
        myWebView.setWebViewClient(new myWebClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(url);
    }

    public class myWebClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }

}
