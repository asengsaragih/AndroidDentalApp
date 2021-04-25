package com.mobile.dental.model;

import java.io.Serializable;

public class Chat implements Serializable {
    private final String text;
    private final String time;
    private final int type;

    public Chat(String text, String time, int type) {
        this.text = text;
        this.time = time;
        this.type = type;
    }
    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public int getType() {
        return type;
    }
}
