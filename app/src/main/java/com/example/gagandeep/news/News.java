package com.example.gagandeep.news;

/**
 * Created by gagandeep on 9/24/17.
 */

public class News {
    private String head, body, web, url, time;

    public News(String head, String body, String web, String url, String time) {
        this.head = head;
        this.body = body;
        this.web = web;
        this.url = url;
        this.time = time;
    }

    public String getHead(){
        return head;
    }
    public String getBody(){
        return body;
    }
    public String getWeb(){
        return web;
    }
    public String getUrl(){
        return url;
    }
    public String getTime(){
        return time;
    }
}
