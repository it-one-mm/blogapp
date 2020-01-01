package com.itonemm.blogapp;

public class Article {

    public String newsAuthor;
    public String newsTitle;
    public String newsDesc;
    public String newsImageLink;
    public String newsLink;
    public String newsDate;

    public Article(String newsAuthor, String newsTitle, String newsDesc, String newsImageLink, String newsLink) {
        this.newsAuthor = newsAuthor;
        this.newsTitle = newsTitle;
        this.newsDesc = newsDesc;
        this.newsImageLink = newsImageLink;
        this.newsLink = newsLink;
    }

    public Article(String newsAuthor, String newsTitle, String newsDesc, String newsImageLink, String newsLink, String newsDate) {
        this.newsAuthor = newsAuthor;
        this.newsTitle = newsTitle;
        this.newsDesc = newsDesc;
        this.newsImageLink = newsImageLink;
        this.newsLink = newsLink;
        this.newsDate = newsDate;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getNewsImageLink() {
        return newsImageLink;
    }

    public void setNewsImageLink(String newsImageLink) {
        this.newsImageLink = newsImageLink;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }
}
