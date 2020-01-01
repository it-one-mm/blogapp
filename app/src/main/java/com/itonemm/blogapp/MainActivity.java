package com.itonemm.blogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView newslist;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         newslist=findViewById(R.id.newslist);
         progressBar=findViewById(R.id.loading);
         new Task().execute();


    }

    public class Task extends AsyncTask<Void,Void,Void>
    {


        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            newslist.setVisibility(View.GONE);
        }
        @Override
        protected Void doInBackground(Void... voids) {

            String url="https://newsapi.org/v2/top-headlines?country=us&apiKey=6307604bda9d47868c00dcc060da0bfe&category=sport";
            final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(

                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            final ArrayList<Article> articleArrayList=new ArrayList<Article>();
                            JSONArray articles= null;
                            try {
                                articles = response.getJSONArray("articles");
                                for(int i=0;i<articles.length();i++)
                                {
                                    JSONObject article= null;

                                    article = articles.getJSONObject(i);

                                    try {
                                        Article article1=new Article(
                                                article.getString("author"),
                                                article.getString("title"),
                                                article.getString("description"),
                                                article.getString("urlToImage"),
                                                article.getString("url"),
                                                article.getString("publishedAt")
                                        );
                                        articleArrayList.add(article1);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ArticleAdapter articleAdapter=new ArticleAdapter(articleArrayList,getApplicationContext());

                                            newslist.setAdapter(articleAdapter);
                                            newslist.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                                            newslist.setVisibility(View.VISIBLE);
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            }
                            catch (Exception ex)
                            {
                                Log.e("JSON Error",ex.getMessage());
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("VolleyError",error.getMessage());
                        }
                    }
            );
            jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 50000;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
            requestQueue.add(jsonObjectRequest);
            return null;
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
