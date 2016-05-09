package com.eric.imitate.icsdn.Bean;

/**
 * Created by Administrator on 2015/11/16.
 */
public class NewsItem {
    private int id;
    private String title;
    private String link;
    private String date;
    private String imgLink;
    private String content;
    private int newsType;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NewsItem() {
    }

    public NewsItem(String content, String title, int newsType, String link, String imgLink, int id, String date) {
        this.content = content;
        this.title = title;
        this.newsType = newsType;
        this.link = link;
        this.imgLink = imgLink;
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", date='" + date + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", newsType=" + newsType +
                '}';
    }
}
