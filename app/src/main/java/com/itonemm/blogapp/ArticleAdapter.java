package com.itonemm.blogapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {

    Context context;
ArrayList<Article> articles=new ArrayList<Article>();

    public ArticleAdapter(ArrayList<Article> articles,Context context) {
        this.articles = articles;
        this.context=context;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.newsrows,parent,false);
        ArticleHolder articleHolder=new ArticleHolder(view);
        return articleHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {

        final Article article=articles.get(position);

        holder.newsTitle.setText(article.newsTitle);
        holder.newsAuthor.setText(article.newsAuthor);
        holder.newsDate.setText(article.newsDate);
        holder.newsDesc.setText(article.newsDesc);
        Glide.with(context)
                .load(article.newsImageLink)
                .into(holder.newsImage);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,NewsDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                NewsDetailActivity.url=article.newsLink;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder{
       ImageView newsImage;
       RelativeLayout relativeLayout;
       TextView newsDate,newsTitle,newsDesc,newsAuthor;
        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
            newsImage=itemView.findViewById(R.id.newsImage);
            newsDate=itemView.findViewById(R.id.newsDate);
            newsAuthor=itemView.findViewById(R.id.newsAuthor);
            newsTitle=itemView.findViewById(R.id.newsTitle);
            newsDesc=itemView.findViewById(R.id.newsDesc);
            relativeLayout=itemView.findViewById(R.id.newslayout);
        }
    }
}
