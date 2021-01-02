package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataLabelingSystem {
    private DataSet dataSet;
    private User currUser;
    private User [] users;
    private DataLabeler dataLabeler;

    public DataSet getDataSet() {
        return dataSet;
    }

    public User[] getUsers() {
        return users;
    }

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }



    public void createDataset(String path){
        DataSetFunction dataSetFunction = new DataSetFunction();
        this.dataSet = dataSetFunction.loadDataSet(path);
    }

    public void createUser(String path){
        JSONArray userList;
        ReadJsonFile jsonReader = new ReadJsonFile();
        JSONObject jsonObject = jsonReader.readJson(path);
        userList = (JSONArray) jsonObject.get("Users");

        this.users = new User[userList.size()];

        for(int i=0; i < userList.size(); i++){
            JSONObject userObj = (JSONObject) userList.get(i);
            int userId = (int) (long) userObj.get("user id");
            String userName = (String) userObj.get("user name");
            String userType = (String) userObj.get("user type");
            String userPassword = (String) userObj.get("password");
            double consistencyCheckProbability = (double) userObj.get("consistencyCheckProbability");
            this.users[i] = new User(userId, userName,userPassword ,userType, consistencyCheckProbability);
        }

    }

    public DataLabeler getDataLabeler() {
        return dataLabeler;
    }

    public void setDataLabeler(DataLabeler dataLabeler) {
        this.dataLabeler = dataLabeler;
    }

    public void createLabel(){
        if (currUser.getUserType().equals("defaultBot")){
            String labelerList [] = {"Random Labeler", "Machine Learning Labeler", "Position Labeler"};
            for (int i=0; i<labelerList.length; i++){
                System.out.printf("%d: %s\n",i+1,labelerList[i]);
            }
            System.out.println("Select Labeler Type By Shown Index: ");
            Scanner scan = new Scanner(System.in);
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    this.dataLabeler = new RandomLabeler(this.dataSet, this.currUser);
                    this.dataLabeler.labelData();
                    break;

                case 2:
                    this.dataLabeler = new MachineLearningLabeler(this.dataSet, this.currUser);
                    this.dataLabeler.labelData();
                    break;
                case 3:
                    this.dataLabeler = new PositionLabeler(this.dataSet, this.currUser);
                    this.dataLabeler.labelData();
                    break;
                default:
                    System.out.println("Invalid Index..");


            }
        }
        else{
            this.dataLabeler = new UserLabeler(this.dataSet, this.currUser);
            dataLabeler.labelData();
        }
//

    }

}
