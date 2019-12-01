package com.example.emr.Models;

import java.util.ArrayList;

public class UserLog {

    private String _id;
    private String pid;
    private String date;
    private String heartbeat;
    private String url;
    private ArrayList<UserLog> result;

    public ArrayList<UserLog> getResult() {
        return result;
    }

    public void setResult(ArrayList<UserLog> result) {
        this.result = result;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(String heartbeat) {
        this.heartbeat = heartbeat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
