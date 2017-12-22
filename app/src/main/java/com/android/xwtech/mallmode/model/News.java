package com.android.xwtech.mallmode.model;

/**
 * Created by XW-Laq on 2017/12/18.
 */

public class News {

    /**
     * news_id : 1
     * type_id : 1
     * news_title : 《时代》杂志公布2016十大烂片：超级英雄集体中枪
     * news_author : 快科技
     * news_img : http://static.cnbetacdn.com/article/2016/1207/68ee34724f2bd6d.jpg
     * news_viewtimes : 84
     * news_pubdate : 2016-12-07
     * news_sort : 7
     * news_isfaved : false
     */

    private int news_id;
    private int type_id;
    private String news_title;
    private String news_author;
    private String news_img;
    private int news_viewtimes;
    private String news_pubdate;
    private int news_sort;
    private boolean news_isfaved;
    private boolean isReaded;

    public boolean isReaded() {
        return isReaded;
    }

    public void setReaded(boolean readed) {
        isReaded = readed;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_author() {
        return news_author;
    }

    public void setNews_author(String news_author) {
        this.news_author = news_author;
    }

    public String getNews_img() {
        return news_img;
    }

    public void setNews_img(String news_img) {
        this.news_img = news_img;
    }

    public int getNews_viewtimes() {
        return news_viewtimes;
    }

    public void setNews_viewtimes(int news_viewtimes) {
        this.news_viewtimes = news_viewtimes;
    }

    public String getNews_pubdate() {
        return news_pubdate;
    }

    public void setNews_pubdate(String news_pubdate) {
        this.news_pubdate = news_pubdate;
    }

    public int getNews_sort() {
        return news_sort;
    }

    public void setNews_sort(int news_sort) {
        this.news_sort = news_sort;
    }

    public boolean isNews_isfaved() {
        return news_isfaved;
    }

    public void setNews_isfaved(boolean news_isfaved) {
        this.news_isfaved = news_isfaved;
    }
}
