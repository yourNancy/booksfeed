package com.example.booksfeed.ui.home;

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

import com.example.booksfeed.R;

public final class HomeFragment extends Fragment {

    private String urlSearch = "https://www.goodreads.com/genres";
    WebView myWebView;
    private ProgressDialog progress;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_home);

        myWebView = (WebView) findViewById(R.id.text_home);
        if (savedInstanceState != null)
            myWebView.restoreState(savedInstanceState);
        else
            myWebView.loadUrl("http://www.google.com");
    }*/

   /* @Override
    public void onPause() {
        super.onPause();
        String url = myWebView.getUrl();
        Bundle out = new Bundle();
        myWebView.saveState(out);
        out.putString("url", url);
        //save the bundle to shared prefrence
        saveToPreferences(out);
    }

    private void saveToPreferences(Bundle out) {
    }*/


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home, container, false);

        final WebView myWebView = myView.findViewById(R.id.text_home);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        //improve webview performance
        myWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myWebView.getSettings().setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        myWebView.getSettings().setEnableSmoothTransition(true);
        webSettings.setAllowContentAccess(true);
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        //progress bar
        progress = ProgressDialog.show(getActivity(), "Please wait...",
                "BooksFeed is loading...", true);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView myView, String urlSearch) {
                myWebView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByTagName('header')[0];"
                        + "head.parentNode.removeChild(head);" +
                        "})()");
                if (progress.isShowing()) {
                    progress.dismiss();
                }
            }
        });

        myWebView.loadUrl(urlSearch);


        myWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() != KeyEvent.ACTION_DOWN)
                    return true;
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (myWebView.canGoBack()) {
                        myWebView.goBack();
                        /*Dlog.d("canGoBack");*/
                    } else {
                        /*Dlog.d("canNotGoBack");*/
                        (getActivity()).onBackPressed();
                    }
                    return true;
                }
                return false;
            }
        });

        return myView;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        myWebView.onResume();
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    /*@Override
    public void onDestroy() {
        super.onDestroy();
    }*/

    public void onBackPressed() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
