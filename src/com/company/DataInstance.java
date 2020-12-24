package com.company;

import org.json.simple.JSONObject;

public class DataInstance {
    private int id;
    private String instane;

    public DataInstance(int id, String instane) {
        this.id = id;
        this.instane = instane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstane() {
        return instane;
    }

    public void setInstane(String instane) {
        this.instane = instane;
    }

    public JSONObject getJsonObject(){
        JSONObject jsonOjb = new JSONObject();

        jsonOjb.put("id", this.id);
        jsonOjb.put("instance", this.instane);
        return jsonOjb;
    }
}

