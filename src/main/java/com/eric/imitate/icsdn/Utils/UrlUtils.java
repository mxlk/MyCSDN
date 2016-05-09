package com.eric.imitate.icsdn.Utils;

import com.eric.imitate.icsdn.Bean.Constants;

/**
 * Created by Administrator on 2015/11/16.
 */
public class UrlUtils {
    public static final String NEWS_LIST_URL = "http://www.csdn.net/headlines.html";
    public static final String NEWS_LIST_URL_MOBILE = "http://mobile.csdn.net/mobile";
    public static final String NEWS_LIST_URL_YEJIE = "http://news.csdn.net/news";
    public static final String NEWS_LIST_URL_PROGRAMMER = "http://programmer.csdn.net/programmer";
    public static final String NEWS_LIST_URL_CLOUD = "http://cloud.csdn.net/cloud";
    public static final String NEWS_LIST_URL_RESDEV = "http://sd.csdn.net/sd";

    public static String generateUrl(int newsType, int currentPage){
        currentPage = currentPage >  0 ? currentPage : 1;
        String urlStr = "";
        switch (newsType){
            case Constants.NEWS_TYPE_MOBILE:
                urlStr += NEWS_LIST_URL_MOBILE;
                break;
            case Constants.NEWS_TYPE_YEJIE:
                urlStr += NEWS_LIST_URL_YEJIE;
                break;
            case Constants.NEWS_TYPE_PROGRAMMER:
                urlStr += NEWS_LIST_URL_PROGRAMMER;
                break;
            case Constants.NEWS_TYPE_CLOUD:
                urlStr += NEWS_LIST_URL_CLOUD;
                break;
            case Constants.NEWS_TYPE_RESDEV:
                urlStr += NEWS_LIST_URL_RESDEV;
                break;
        }

        urlStr += "/" + currentPage;
        return urlStr;
    }
}
