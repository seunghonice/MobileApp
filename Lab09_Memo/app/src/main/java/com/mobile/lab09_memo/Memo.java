package com.mobile.lab09_memo;

/**
 * Created by HongSeungho on 2017. 5. 30..
 */

public class Memo {
    private String date;
    private String contents;

    public Memo(String date, String contents) {
        this.date = date;
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
