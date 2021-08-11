package com.example.splash;

public class TextModel {
    String text,time;

    public TextModel() {
    }

    public TextModel(String text, String time) {
        this.text = text;
        this.time=time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
