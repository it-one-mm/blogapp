package com.itonemm.blogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class NewsDetailActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;
    public static String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
         webView=findViewById(R.id.newsview);
        webView.getSettings().setJavaScriptEnabled(true);
        progressBar=findViewById(R.id.progressbar);
new Task().execute();

    }

    public class Task extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   webView.loadUrl(url);
                   webView.setVisibility(View.VISIBLE);
                   progressBar.setVisibility(View.GONE);
               }

           });
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
