package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class DataLabeler {
    protected DataSet datasetInstance;
    protected User user[];
    protected DataLabel dataLabelList[];

    public DataLabeler(DataSet datasetInstance, User[] user) {
        this.datasetInstance = datasetInstance;
        this.user = user;
        this.dataLabelList = new DataLabel[datasetInstance.getInstance().length];

    }

    public DataSet getDatasetInstance() {
        return datasetInstance;
    }

    public void setDatasetInstance(DataSet datasetInstance) {
        this.datasetInstance = datasetInstance;
    }

    public User[] getUser() {
        return user;
    }

    public void setUser(User[] user) {
        this.user = user;
    }

    public abstract void labelData();

    public JSONObject getJsonObject(){
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<this.dataLabelList.length; i++){
            if(this.dataLabelList[i]!=null) {
                jsonArray.add(this.dataLabelList[i].getJsonObject());
            }
        }
        jsonObj.put("class label assignment", jsonArray);
        JSONArray userArray = new JSONArray();
        for(int i=0; i<this.user.length; i++){
            userArray.add(this.user[i].getJsonObject());
        }
        jsonObj.put("users", userArray);

        return jsonObj;
    }

}
