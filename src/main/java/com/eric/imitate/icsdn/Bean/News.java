package com.eric.imitate.icsdn.Bean;

/**
 * Created by Administrator on 2015/12/3.
 */
public class News {
    public static interface NewsType{
        public static final int TITLE = 1;
        public static final int SUMMARY = 2;
        public static final int CONTENT = 3;
        public static final int IMG = 4;
        public static final int BOLD_TITLE = 5;
    }

    private String title;
    private String summary;
    private String content;
    private String imgLink;
    private String boldTitle;
    private int type;


    public String getBoldTitle() {
        return boldTitle;
    }

    public void setBoldTitle(String boldTitle) {
        this.boldTitle = boldTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "News{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", type=" + type +
                '}';
    }
}
