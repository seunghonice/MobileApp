package com.mobile.lab09;

/**
 * Created by HongSeungho on 2017. 5. 11..
 */

public class Site {
    private String name;
    private String url;

    public Site(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
