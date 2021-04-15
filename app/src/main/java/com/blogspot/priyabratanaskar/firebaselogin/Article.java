package com.blogspot.priyabratanaskar.firebaselogin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Priyabrata Naskar on 15-04-2021.
 */
public class Article {

    @SerializedName("source")
    private SourceModel source;
    @SerializedName("author")
    private String newsAuthorName;
    @SerializedName("title")
    private String newsTitle;
    @SerializedName("description")
    private String newsDescription;
    @SerializedName("urlToImage")
    private String newsImageResource;
    @SerializedName("publishedAt")
    private String newsPublishTime;
    @SerializedName("content")
    private String content;

    public SourceModel getSource() {
        return source;
    }

    public void setSource(SourceModel source) {
        this.source = source;
    }

    public String getNewsAuthorName() {
        if (newsAuthorName==null){
            newsAuthorName = "Unknown";
        }
        return newsAuthorName;
    }

    public void setNewsAuthorName(String newsAuthorName) {
        if (newsAuthorName==null){
            newsAuthorName = "Author: Unknown";
        }else {
            this.newsAuthorName = "Author: " +newsAuthorName;
        }
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        if (newsDescription==null){
            newsDescription = "Description Not Available";
        }
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsImageResource() {
        return newsImageResource;
    }

    public void setNewsImageResource(String newsImageResource) {
        this.newsImageResource = newsImageResource;
    }

    public String getNewsPublishTime() {
        return newsPublishTime;
    }

    public void setNewsPublishTime(String newsPublishTime) {
        this.newsPublishTime = newsPublishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(SourceModel source, String newsAuthorName,
                   String newsTitle, String newsDescription, String newsImageResource, String newsPublishTime, String content) {
        this.source = source;
        this.newsAuthorName = newsAuthorName;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsImageResource = newsImageResource;
        this.newsPublishTime = newsPublishTime;
        this.content = content;
    }
}
