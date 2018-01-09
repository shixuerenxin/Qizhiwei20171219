package com.bwei.qizhiwei20171219.bean;

/**
 * author:Created by QiZhiWei on 2017/12/19.
 */

public class User {

    private String page;
    private String keywords;

    public User(String page, String keywords) {
        this.page = page;
        this.keywords = keywords;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "User{" +
                "page='" + page + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
