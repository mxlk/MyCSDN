package com.eric.imitate.icsdn.Biz;

import android.util.Log;

import com.eric.imitate.icsdn.Bean.CommonException;
import com.eric.imitate.icsdn.Bean.News;
import com.eric.imitate.icsdn.Bean.NewsItem;
import com.eric.imitate.icsdn.Bean.NewsToDetail;
import com.eric.imitate.icsdn.Utils.DataUtils;
import com.eric.imitate.icsdn.Utils.UrlUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class NewsItemBiz {
    public List<NewsItem> getNewsItem(int newsType, int currentPage) throws CommonException{
        String urlStr = UrlUtils.generateUrl(newsType, currentPage);
//        String htmlStr = DataUtils.doGet(urlStr);
//        String htmlStr = Jsoup.connect(urlStr).toString();
//        Log.d("TAG", "htmlStr = " + htmlStr);
        List<NewsItem> newsItems = new ArrayList<NewsItem>();
        NewsItem newsItem = null;

//        Document document = Jsoup.parse(htmlStr);

        Document document = null;
        try {
            document = Jsoup.connect(urlStr).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "document = " + document);
        Elements units = document.getElementsByClass("unit");
        Log.d("TAG", "units = " + units);
        for (int i = 0; i < units.size(); i++){
            newsItem = new NewsItem();
            newsItem.setNewsType(newsType);

            Element unit_ele = units.get(i);
            Element h1_ele = unit_ele.getElementsByTag("h1").get(0);
            Element h1_a_ele = h1_ele.child(0);
            String title = h1_a_ele.text();
            String href = h1_a_ele.attr("href");

            newsItem.setLink(href);
            newsItem.setTitle(title);

            Element h4_ele = unit_ele.getElementsByTag("h4").get(0);
            Element ago_ele = h4_ele.getElementsByClass("ago").get(0);
            String date = ago_ele.text();
            newsItem.setDate(date);

            Element dl_ele = unit_ele.getElementsByTag("dl").get(0);
            Element dt_ele = dl_ele.child(0);

            try{
//                可能没有图片
                Element img_ele = dt_ele.child(0);
                String imgLink = img_ele.child(0).attr("src");
                Log.d("TAG", "imgLInk = " + imgLink);
                newsItem.setImgLink(imgLink);
            }catch (IndexOutOfBoundsException e){

            }
            Element content_ele = dl_ele.child(1); //dd
            String content = content_ele.text();
            newsItem.setContent(content);

            newsItems.add(newsItem);

        }
        return newsItems;
    }


    public NewsToDetail getNews(String urlString) throws CommonException{
        NewsToDetail newsToDetail = new NewsToDetail();
        List<News> newses = new ArrayList<News>();
        Document document = null;

        String htmlStr = DataUtils.doGet(urlString);
        Log.d("TAG", "html = " + htmlStr);
        document = Jsoup.parse(htmlStr);
        Log.d("TAG", "document = " + document);

//        try {
//            document = Jsoup.connect(urlString).get();
//            Log.d("TAG", "document = " + document);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Element leftEle = document.getElementsByClass("left").get(0);
        Log.d("TAG", "left = " + leftEle);
        Element detailEle = leftEle.select(".detail").get(0);
//        Element detailEle = document.getElementsByClass("wrapper").get(0);
        Log.d("Detail", "detail = " +  detailEle);
//        标题
        Element titleEle = detailEle.select("h1.title").get(0);
        News news = new News();
        news.setTitle(titleEle.text());
        news.setType(News.NewsType.TITLE);
//        newses.add(news);
//          摘要
        Element summaryEle = detailEle.select("div.summary").get(0);
//        news = new News();
        news.setSummary(summaryEle.text());
        news.setType(News.NewsType.SUMMARY);
//        newses.add(news);
//        内容
        Element contentEle = detailEle.select("div.con.news_content").get(0);
        Elements childrenEle = contentEle.children();
        for (Element child : childrenEle) {
            Elements imgEles = child.getElementsByTag("img");
            if(imgEles.size() > 0){
                for(Element imgEle : imgEles){
                    if(imgEle.attr("src").equals("")){
                        continue;
                    }
//                    news = new News();
                    news.setImgLink(imgEle.attr("src"));
                    news.setType(News.NewsType.IMG);
//                    newses.add(news);
                }
            }
            imgEles.remove();

            if (child.text().equals("")){
                continue;
            }
//            news = new News();
            news.setType(News.NewsType.CONTENT);

            try {
                if (child.children().size() == 1) {
                    Element cc = child.child(0);
                    if (cc.tagName().equals("b")) {
//                        news.setBoldTitle(cc.text());
                        news.setType(News.NewsType.BOLD_TITLE);
                    }
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
            news.setContent(child.outerHtml());
            newses.add(news);
        }
        newsToDetail.setNewses(newses);
        return newsToDetail;
    }
    public String getDetail(String urlString){
        Document document = null;

        String htmlStr = null;
        try {
            htmlStr = DataUtils.doGet(urlString);
        } catch (CommonException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "html = " + htmlStr);
        document = Jsoup.parse(htmlStr);
        Log.d("TAG", "document = " + document);

//        try {
//            document = Jsoup.connect(urlString).get();
//            Log.d("TAG", "document = " + document);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Element leftEle = document.getElementsByClass("left").get(0);
        Log.d("TAG", "left = " + leftEle);
        Element detailEle = leftEle.select(".detail").get(0);
        Log.d("DE", "DETAIL = " + detailEle.text());
        detailEle.select(".tag").remove();
        detailEle.select("h1").remove();
        detailEle.select("h4 .tit_bar").remove();
        detailEle.select(".detail").remove();
        detailEle.select(".related").remove();
        detailEle.select(".relational").remove();
        detailEle.select("p.copyright").remove();
        Elements images = detailEle.getElementsByTag("img");

        for(Element image : images){
            if(image.attr("src") != null){
                image.attr("style", "width:100%;height:auto");
            }
        }
        return detailEle.toString();
    }
}
