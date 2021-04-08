package com.blogspot.priyabratanaskar.firebaselogin;

/**
 * Created by Priyabrata Naskar on 08-04-2021.
 */
public class News {
    private String newsAuthorName;
    private String newsTitle;
    private String newsDescription;
    private String newsImageResource;
    private String newsContent;
    private String newsPublishTime;
    private String sourceName;

    public News(String newsAuthorName, String newsTitle, String newsDescription, String newsImageResource) {
        this.newsAuthorName = newsAuthorName;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsImageResource = newsImageResource;
    }

    public News(String newsAuthorName, String newsTitle, String newsDescription,
                String newsImageResource, String newsContent, String newsPublishTime, String sourceName) {
        this.newsAuthorName = newsAuthorName;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.newsImageResource = newsImageResource;
        this.newsContent = newsContent;
        this.newsPublishTime = newsPublishTime;
        this.sourceName = sourceName;
    }

    public String getNewsAuthorName() {
        return newsAuthorName;
    }

    public void setNewsAuthorName(String newsAuthorName) {
        this.newsAuthorName = newsAuthorName;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
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

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsPublishTime() {
        return newsPublishTime;
    }

    public void setNewsPublishTime(String newsPublishTime) {
        this.newsPublishTime = newsPublishTime;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}