package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserLog {
    private String currPath;
    public UserLog(){
        this.currPath = System.getProperty("user.dir") +  "/userLog/";
    }
    public DataInstance[] getUserLog(User user){
        String fileName;
        fileName = this.currPath + user.getId() + "_" + user.getUserName() + "_" + "Data.json";
        DataInstance [] labeledInstances;
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            jsonObject = (JSONObject) obj;
            JSONArray instancesArray;
            instancesArray = (JSONArray) jsonObject.get("instances");
            labeledInstances = new DataInstance[instancesArray.size()];
            for (int i = 0; i < instancesArray.size(); i++) {
                JSONObject instObj = (JSONObject) instancesArray.get(i);
                labeledInstances[i] = new DataInstance((int) (long) instObj.get("id"), (String) instObj.get("instance"));
            }

            return labeledInstances;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ParseException e) {
        }

        return null;
    }

    public void createUserLog(DataInstance [] dataInstances, User user){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String path = this.currPath + user.getId() + "_" + user.getUserName() + "_" + "Data.json";
        for(DataInstance instance: dataInstances){
            if (instance != null) {
                jsonArray.add(instance.getJsonObject());
            }
        }
        jsonObject.put("instances", jsonArray);
        jsonObject.put("User", user.getJsonObject());
        try(FileWriter file = new FileWriter(path)){
            file.write(jsonObject.toJSONString());
            file.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}