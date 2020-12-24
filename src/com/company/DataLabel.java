package com.company;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DataLabel {
    private DataInstance instance;
    private ClassLabel classLabelList[];
    private User user;
    private Date dateTime;

    public DataLabel(){
        this.dateTime = new Date();
        this.classLabelList = new ClassLabel[1];
    }

    public DataLabel(DataInstance instance, User user) {
        this.instance = instance;
        this.user = user;
        this.dateTime = new Date();
        this.classLabelList = new ClassLabel[1];
    }

    public DataInstance getInstance() {
        return instance;
    }

    public void setInstance(DataInstance instance) {
        this.instance = instance;
    }

    public ClassLabel[] getClassLabelList() {
        return classLabelList;
    }

    public void setClassLabelList(ClassLabel classLabelList[]) {
        this.classLabelList = classLabelList;
    }

    public void addClassLabel(ClassLabel classLabel){
        int currIndex = 0;
        for(int i=0; i<this.classLabelList.length; i++) {
            if(this.classLabelList[i]==null) {
                currIndex = i;
                break;
            }
        }
        this.classLabelList[currIndex] = classLabel;
        ClassLabel [] newList = new ClassLabel[this.classLabelList.length+1];
        for(int i=0; i<this.classLabelList.length; i++){
            newList[i] = this.classLabelList[i];
        }
        this.classLabelList = newList;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public JSONObject getJsonObject(){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("instance id", this.instance.getId());
//        int classLabelIds [] = new int[this.classLabelList.length]
        JSONArray jsonCLID = new JSONArray();
        for(int i=0; i<this.classLabelList.length-1; i++){
            jsonCLID.add(this.classLabelList[i].getId());
        }
        jsonObj.put("class label ids", jsonCLID);
        jsonObj.put("user id", user.getId());

        String pattern = "dd-M-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.dateTime);
        jsonObj.put("datetime", date);

        return jsonObj;
    }
}