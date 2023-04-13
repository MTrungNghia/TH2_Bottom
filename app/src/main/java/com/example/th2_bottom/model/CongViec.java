package com.example.th2_bottom.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id;
    private String name, content, date, status;
    private int together;

    public CongViec() {
    }

    public CongViec(int id, String name, String content, String date, String status, int together) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;
        this.status = status;
        this.together = together;
    }

    public CongViec(String name, String content, String date, String status, int together) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.status = status;
        this.together = together;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTogether() {
        return together;
    }

    public void setTogether(int together) {
        this.together = together;
    }
}
