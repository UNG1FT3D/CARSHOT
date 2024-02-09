package com.example.project4thsem;

public class historyDetail {

    String date;
    String time;
    String type;
    String plan;

    public historyDetail(){}

    public historyDetail(String date, String time, String type, String plan) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.plan = plan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
