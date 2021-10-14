package com.techrz.fevrt.firebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class webview extends AppCompatActivity {
    private WebView webView;
    static long point=0;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView= findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(home.link);
        TextView mTextField = findViewById(R.id.point);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        ImageView back=findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());
        ImageView forward= findViewById(R.id.forward);
        forward.setOnClickListener(v -> forward());
        ImageView reload= findViewById(R.id.reload);
        reload.setOnClickListener(v -> reload());
        ImageView openInBrowser = findViewById(R.id.open);
        openInBrowser.setOnClickListener(v -> openInBrowser());



        long maxCounter = 300000000;
        long diff = 1000;

        new CountDownTimer(maxCounter , diff ) {

            public void onTick(long millisUntilFinished) {
                long diff = maxCounter - millisUntilFinished;
                mTextField.setText("seconds completed: " +diff  / 1000);
                //here you can have your logic to set text to edittext
                point= diff  / 1000;
            }

            public void onFinish() {
                mTextField.setText("Max limit reached");
            }

        }.start();
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            database d= new database();
            d.saveData();
            super.onBackPressed();

        }

    }

    public void forward(){
        if(webView.canGoForward()){
            webView.goForward();
        }
    }
    public void reload(){
        webView.reload();
    }
    public void openInBrowser(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(home.link));
        startActivity(intent);
    }
    @Override
    public void onStop(){
        super.onStop();
        //database d= new database();
        //d.saveData();
        finish();

    }
}