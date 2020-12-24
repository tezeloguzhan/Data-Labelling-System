package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataLabelingSystem {
    private com.company.DataSet dataSet;
    private com.company.User users [];
    private com.company.DataLabeler dataLabeler;

    public void createDataset(String path){
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

        this.dataSet = new com.company.DataSet(datasetID, datasetName, maximumLabels, classLabelLength, instanceLength);

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


    }

    public void createUser(String path){
        JSONArray userList;
        ReadJsonFile jsonReader = new ReadJsonFile();
        JSONObject jsonObject = jsonReader.readJson(path);
        userList = (JSONArray) jsonObject.get("Users");

        this.users = new com.company.User[userList.size()];

        for(int i=0; i < userList.size(); i++){
            JSONObject userObj = (JSONObject) userList.get(i);
            int userId = (int) (long) userObj.get("user id");
            String userName = (String) userObj.get("user name");
            String userType = (String) userObj.get("user Type");
            double consistencyCheckProbability = (double) userObj.get("consistencyCheckProbability");
            this.users[i] = new com.company.User(userId, userName, userType, consistencyCheckProbability);
        }

    }

    public com.company.DataLabeler getDataLabeler() {
        return dataLabeler;
    }

    public void setDataLabeler(com.company.DataLabeler dataLabeler) {
        this.dataLabeler = dataLabeler;
    }

    public void createLabel(){
        String labelerList [] = {"Random Labeler", "Machine Learning Labeler"};
        for (int i=0; i<labelerList.length; i++){
            System.out.printf("%d: %s\n",i+1,labelerList[i]);
        }
        System.out.println("Select Labeler Type By Shown Index: ");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        switch (option) {
            case 1:
                this.dataLabeler = new RandomLabeler(this.dataSet, this.users);
                this.dataLabeler.labelData();
                break;

            case 2:
                this.dataLabeler = new MachineLearningLabeler(this.dataSet, this.users);
                this.dataLabeler.labelData();
                break;

            default:
                System.out.println("Invalid Index..");


        }

    }

    public void saveToJson(String path){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Input Dataset", this.dataSet.getJsonObject());
        jsonObj.put("Output", this.dataLabeler.getJsonObject());
        try(FileWriter file = new FileWriter(path)){
            file.write(jsonObj.toJSONString());
            file.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void terminalOutput(){
        JSONObject jsonObj = this.dataLabeler.getJsonObject();
        System.out.println(jsonObj.toJSONString());
    }


    public void ReportOutput(String path){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Number of Instances Labeled", this.dataLabeler.dataLabelList.length);
        jsonObj.put("Completeness Percentage", (this.dataLabeler.dataLabelList.length/this.dataSet.getInstance().length)*100);
        JSONArray uniqueArray = new JSONArray();
        for(int i=0; i<this.dataLabeler.dataLabelList.length; i++){
            if(this.dataLabeler.dataLabelList[i]!=null){
                if(this.dataLabeler.dataLabelList[i].getClassLabelList().length <= 1){
                    uniqueArray.add(this.dataLabeler.dataLabelList[i].getInstance().getJsonObject());
                }
            }
        }
        jsonObj.put("Instances with unique class Label", uniqueArray);
        JSONArray userArray = new JSONArray();
        for(int i=0; i<this.dataLabeler.dataLabelList.length; i++){
            if(this.dataLabeler.dataLabelList[i]!=null){
                userArray.add(this.dataLabeler.dataLabelList[i].getUser().getJsonObject());
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
