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
        labelerSystem.createLabel();
        labelerSystem.terminalOutput();
        labelerSystem.saveToJson(currPath + "\\Labeled_Data_Output.json");       //output path
        labelerSystem.ReportOutput(currPath + "\\Report_Output.json");       //output path


    }
}
