package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ReadJsonFile {
    public JSONParser parser;

    ReadJsonFile() {
        this.parser = new JSONParser();
    }

    public JSONObject readJson(String path) {
        try(FileReader reader = new FileReader(path)){
            Object obj = this.parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
