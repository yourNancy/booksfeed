package com.example.booksfeed;

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
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class WishlistFragment extends Fragment {

    private String urlSearch3 = "https://www.goodreads.com/review/list/107246746?ref=nav_mybooks";
    WebView mWebView;
    private ProgressDialog progress2;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_wishlist, container, false);

        final WebView mWebView = myView.findViewById(R.id.webview_wishlist);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        //improve webview performance
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        mWebView.getSettings().setEnableSmoothTransition(true);
        webSettings.setAllowContentAccess(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        //progress bar
        progress2 = ProgressDialog.show( getActivity(), "Please wait...",
                "Wishlist is loading...", true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView mWebView, String urlSearch3) {
                mWebView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByTagName('header')[0];"
                        + "head.parentNode.removeChild(head);" +
                        "})()");
                if (progress2.isShowing()) {
                    progress2.dismiss();
                }
            }
    });

        mWebView.loadUrl(urlSearch3);

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

        /*mWebView.setWebViewClient(new WebViewClient());*/
        return myView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onBackPressed() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}