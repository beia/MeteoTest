package ro.beia.meteotestforecast;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ForecastShowActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_show);

        setupWebView();

        Intent intent = getIntent();
        String urlIntentKey = getString(R.string.intent_api_url_key);

        String url = intent.getStringExtra(urlIntentKey);
        changeWebViewUrl(url);

    }

    private void changeWebViewUrl(String url) {
        mWebView.loadUrl(url);
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        mWebView = findViewById(R.id.webView);
        WebSettings webSettings;
        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        mWebView.setInitialScale(1);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
    }
}


