package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator {
    public void saveToJson(String path, DataSet dataSet, DataLabeler dataLabeler){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Input Dataset", dataSet.getJsonObject());
        jsonObj.put("Output", dataLabeler.getJsonObject());
        try(FileWriter file = new FileWriter(path)){
            file.write(jsonObj.toJSONString());
            file.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void terminalOutput(DataLabeler dataLabeler){
        JSONObject jsonObj = dataLabeler.getJsonObject();
        System.out.println(jsonObj.toJSONString());
    }


    public void ReportOutput(String path, DataLabeler dataLabeler, DataSet dataSet){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Number of Instances Labeled", dataLabeler.dataLabelList.length);
        jsonObj.put("Completeness Percentage", (dataLabeler.dataLabelList.length/dataSet.getInstance().length)*100);
        JSONArray uniqueArray = new JSONArray();
        for(int i=0; i<dataLabeler.dataLabelList.length; i++){
            if(dataLabeler.dataLabelList[i]!=null){
                if(dataLabeler.dataLabelList[i].getClassLabelList().length <= 1){
                    uniqueArray.add(dataLabeler.dataLabelList[i].getInstance().getJsonObject());
                }
            }
        }
        jsonObj.put("Instances with unique class Label", uniqueArray);
        JSONArray userArray = new JSONArray();
        for(int i=0; i<dataLabeler.dataLabelList.length; i++){
            if(dataLabeler.dataLabelList[i]!=null){
                userArray.add(dataLabeler.dataLabelList[i].getUser().getJsonObject());
            }
        }
        jsonObj.put("User Assigned", userArray);
        System.out.println("Report: ");
        System.out.println(jsonObj.toJSONString());
        try(FileWriter file = new FileWriter(path)){
            file.write(jsonObj.toJSONString());
            file.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
