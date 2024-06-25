package com.example.fangtoutiao;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewsDetailsActivity extends AppCompatActivity {

    private NewsInfo.ResultDTO.DataDTO dataDTO;
    private Toolbar toolbar;
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        //初始化控件
        toolbar = findViewById(R.id.toolbar);
        mWebView = findViewById(R.id.webView);
        //获取传递的数据
        dataDTO = (NewsInfo.ResultDTO.DataDTO) getIntent().getSerializableExtra("dataDTO");
        //设置数据
        if (dataDTO!=null){
            toolbar.setTitle(dataDTO.getTitle());
            mWebView.loadUrl(dataDTO.getUrl());
        }
        //返回按键
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}