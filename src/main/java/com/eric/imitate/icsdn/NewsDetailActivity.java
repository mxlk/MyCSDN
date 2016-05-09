package com.eric.imitate.icsdn;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.eric.imitate.icsdn.Bean.News;
import com.eric.imitate.icsdn.Biz.NewsItemBiz;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private RecyclerView recyclerView;
    private NewsItemBiz newsItemBiz = new NewsItemBiz();
    private LinearLayoutManager linearLayoutManager;
//    private NewsDetailAdapter newsDetailAdapter;
    private List<News> news =  new ArrayList<News>();

    private WebView webView;
    public static final String URLSTR = "urlStr";
    public static final String TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        String url_str = getIntent().getStringExtra(URLSTR);
        String title = getIntent().getStringExtra(TITLE);
//toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);
        toolbar.setNavigationIcon(R.drawable.back_icon);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(NewsDetailActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        new LoadDatas().execute(url_str);
        webView = (WebView) findViewById(R.id.webView);
//        recyclerView = (RecyclerView) findViewById(R.id.news_detail);
//        newsDetailAdapter = new NewsDetailAdapter(this,news);
//        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(newsDetailAdapter);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }


    public class LoadDatas extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            Log.d("URL", "URL" + url);

            String detail = newsItemBiz.getDetail(url);

            return detail;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("News", "news = " + news);
            if(news == null) return;
//            newsDetailAdapter.addNewses(news);
            progressBar.setVisibility(View.GONE);
            WebSettings settings = webView.getSettings();
            settings.setDefaultTextEncodingName("utf-8");
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setDefaultFontSize(16);
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);
            webView.loadData(result, "text/html; charset=UTF-8", null);
        }
    }
}
