package com.mobile.dental.model;

public class History {
    private String date;
    private String content;

    public History(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
