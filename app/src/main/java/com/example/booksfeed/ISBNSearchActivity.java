package com.example.booksfeed;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ISBNSearchActivity extends AppCompatActivity {

    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_isbn);

            mWebview  = new WebView(this);

            final Activity activity = this;

            mWebview.loadUrl("https://books.google.com");
            WebSettings webSettings2 = mWebview.getSettings();
            mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript
            setContentView(mWebview);

            mWebview.setWebViewClient(new WebViewClient() {
                @SuppressWarnings("deprecation")
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
                }
                @TargetApi(android.os.Build.VERSION_CODES.M)
                @Override
                public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                    // Redirect to deprecated method, so you can use it in all SDK versions
                    onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                }
            });

        //improve webview performance
        mWebview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebview.getSettings().setAppCacheEnabled(true);
        mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        /*WebSettings.setDomStorageEnabled(true);
        WebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);*/
        webSettings2.setUseWideViewPort(true);
        webSettings2.setSavePassword(true);
        webSettings2.setSaveFormData(true);
        webSettings2.setEnableSmoothTransition(true);
        webSettings2.setAllowContentAccess(true);
        webSettings2.setUseWideViewPort(true);
        webSettings2.setLoadsImagesAutomatically(true);
        webSettings2.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings2.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings2.setEnableSmoothTransition(true);
        webSettings2.setDomStorageEnabled(true);

        }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(ISBNSearchActivity.this, ISBNFragment.class);
        startActivity(intent);
        finish();

    }
}
