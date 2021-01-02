package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.crypto.Data;

public class DataSetFunction {
    private DataSet dataSet;

    public DataSet loadDataSet(String path){
        int datasetID;
        String datasetName;
        int maximumLabels;
        int classLabelLength;
        int instanceLength;
        JSONArray classLabelList;
        JSONArray dataInstanceList;
        ReadJsonFile jsonReader = new ReadJsonFile();
        JSONObject jsonObject = jsonReader.readJson(path);
        datasetID = (int) (long) jsonObject.get("dataset id");
        datasetName = (String) jsonObject.get("dataset name");
        maximumLabels = (int) (long) jsonObject.get("maximum number of labels per instance");
        classLabelList = (JSONArray) jsonObject.get("class labels");
        dataInstanceList = (JSONArray) jsonObject.get("instances");
        classLabelLength = classLabelList.size();
        instanceLength = dataInstanceList.size();

        this.dataSet = new DataSet(datasetID, datasetName, maximumLabels, classLabelLength, instanceLength);

        for(int i=0; i < classLabelLength; i++){
            JSONObject labelObj = (JSONObject) classLabelList.get(i);
            int cId = (int) (long) labelObj.get("label id");
            String cText = (String) labelObj.get("label text");
            this.dataSet.setClassLabel(new ClassLabel(cId, cText));
        }

        for(int i=0; i < instanceLength; i++){
            JSONObject instObj = (JSONObject) dataInstanceList.get(i);
            int instId = (int) (long) instObj.get("id");
            String instText = (String) instObj.get("instance");
            this.dataSet.setInstance(new DataInstance(instId, instText));
        }
        return this.dataSet;
    }
}
