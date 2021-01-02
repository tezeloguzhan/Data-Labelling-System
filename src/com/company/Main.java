package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String currPath = System.getProperty("user.dir");

        DataLabelingSystem labelerSystem = new DataLabelingSystem();
        labelerSystem.createDataset(currPath + "\\LabelingProject_Input-1.json");
        labelerSystem.createUser(currPath + "\\Users.json");
        UserFunction userFunction = new UserFunction(labelerSystem.getUsers());
        User currUser = userFunction.userLogin();
        labelerSystem.setCurrUser(currUser);
        labelerSystem.createLabel();
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.terminalOutput(labelerSystem.getDataLabeler());
        reportGenerator.saveToJson(currPath + "\\Labeled_Data_Output.json", labelerSystem.getDataSet(), labelerSystem.getDataLabeler());       //output path
        reportGenerator.ReportOutput(currPath + "\\Report_Output.json", labelerSystem.getDataLabeler(), labelerSystem.getDataSet());       //output path
    }
}
