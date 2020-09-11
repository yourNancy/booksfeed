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

public class SearchISBNFragment extends Fragment {

        private String urlSearch = "https://www.goodreads.com/search?utf8=%E2%9C%93&q=fiction&search_type=books";
        WebView myWebView;
        private ProgressDialog progress;

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View myView = inflater.inflate(R.layout.activity_search_isbn, container, false);

            final WebView myWebView = myView.findViewById(R.id.webview_search_isbn);
            myWebView.loadUrl("https://www.goodreads.com/search?utf8=%E2%9C%93&q=fiction&search_type=books");
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

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


            //improve webview performance
            myWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            myWebView.getSettings().setAppCacheEnabled(true);
            myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        /*WebSettings.setDomStorageEnabled(true);
        WebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);*/
            webSettings.setUseWideViewPort(true);
            webSettings.setSavePassword(true);
            webSettings.setSaveFormData(true);
            webSettings.setEnableSmoothTransition(true);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setAllowContentAccess(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadsImagesAutomatically(true);
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            webSettings.setEnableSmoothTransition(true);
            webSettings.setDomStorageEnabled(true);

            //progress bar
            progress = ProgressDialog.show(getActivity(), "Please wait...",
                    "Search ISBN page is loading...", true);
            myWebView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView myWebView, String urlSearch) {
                    if (progress.isShowing()) {
                        progress.dismiss();
                    }
                }
            });

            /*myWebView.setWebViewClient(new WebViewClient());*/

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
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }

    }

