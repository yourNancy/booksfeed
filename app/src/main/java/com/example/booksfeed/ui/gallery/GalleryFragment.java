package com.example.booksfeed.ui.gallery;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.booksfeed.R;

public class GalleryFragment extends Fragment {

    public WebView mWebView;
    private ProgressDialog progress3;
    String urlSearch2 = "https://www.goodreads.com/search?utf8=%E2%9C%93&q=Android+Studio&search_type=books&search%5Bfield%5D=on";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mWebView = (WebView) view.findViewById(R.id.webview_search);


        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        mWebView.loadUrl("https://www.goodreads.com/search?utf8=%E2%9C%93&q=Android+Studio&search_type=books&search%5Bfield%5D=on");

        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction()!=KeyEvent.ACTION_DOWN)
                    return true;
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                        /*Dlog.d("canGoBack");*/
                    } else {
                        /*Dlog.d("canNotGoBack");*/
                        (getActivity()).onBackPressed();
                    } return true;
                } return false;
            }
        });

        //improve webview performance
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        /*mWebView.getSettings().setDomStorageEnabled(true);*/
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);

        //progress bar
        progress3 = ProgressDialog.show( getActivity(), "Please wait...",
                "Search book Goodreads page is loading...", true);
        mWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView myWebView, String urlSearch2) {
                if (progress3.isShowing()) {
                    progress3.dismiss();
                }
            }
        });

        // Force links and redirects to open in the WebView instead of in a browser
        /*mWebView.setWebViewClient(new WebViewClient());*/
        return view;
    }

}