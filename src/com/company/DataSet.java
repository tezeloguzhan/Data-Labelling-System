package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataSet {

    private int id;
    private String name;
    private int maximumLabels;
    private ClassLabel classLabel [];
    private DataInstance instance [];

    public DataSet(int id, String name, int maximumLabels, int labelLength, int instLength){
        this.id = id;
        this.name = name;
        this.maximumLabels = maximumLabels;
        this.classLabel = new ClassLabel[labelLength];
        this.instance = new DataInstance[instLength];
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaximumLabels(int maximumLabels) {
        this.maximumLabels = maximumLabels;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaximumLabels() {
        return maximumLabels;
    }

    public ClassLabel[] getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(ClassLabel classLabel) {
        for(int i=0; i<this.classLabel.length; i++) {
            if(this.classLabel[i]==null) {
                this.classLabel[i] = classLabel;
                break;
            }
        }
    }

    public DataInstance[] getInstance() {
        return instance;
    }

    public void setInstance(DataInstance instance) {
        for(int i=0; i<this.instance.length; i++) {
            if(this.instance[i]==null) {
                this.instance[i] = instance;
                break;
            }
        }
    }

    public JSONObject getJsonObject(){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("dataset id", this.id);
        jsonObj.put("dataset name", this.name);
        JSONArray labelArray = new JSONArray();
        for(int i=0; i<this.classLabel.length; i++){
            labelArray.add(this.classLabel[i].getJsonObject());
        }
        jsonObj.put("class labels", labelArray);

        JSONArray instanceArray = new JSONArray();
        for(int i=0; i<this.instance.length; i++){
            instanceArray.add(this.instance[i].getJsonObject());
        }
        jsonObj.put("instances", instanceArray);

        return jsonObj;
    }

}

