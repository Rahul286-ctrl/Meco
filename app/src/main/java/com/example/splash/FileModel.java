package com.example.splash;

public class FileModel {
    String title,vurl,uid;

    public FileModel() {
    }

    public FileModel(String title, String vurl,String uid) {
        this.title = title;
        this.vurl = vurl;
        this.uid=uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
