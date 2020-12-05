import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSON extends JSONArray {

    String availibilityStatus;

    public JSON(String availibilityStatus) {
        super();
        this.availibilityStatus = availibilityStatus;
    }

    public void store(Object key, Object value) {
        JSONObject jo = new JSONObject();
        jo.put(key, value);
        this.add(jo);
    }

    public void update(Object key, Object value) {
        JSONObject jo = new JSONObject();
        jo.put(key, value);

        if (this.contains(jo)) {
            this.add(jo);
        } else {
            throw new Error("key not found!");
        }
    }

    public void delete(Object key, Object value) {
        JSONObject jo = new JSONObject();
        jo.put(key, value);
        this.remove(jo);
    }

    public void display() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(this.toJSONString());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);
    }


    public static Object readJson(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }


    public void writeJson(String filename) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(this.toJSONString());
        String prettyJsonString = gson.toJson(je);
        Files.write(Paths.get(filename), prettyJsonString.getBytes());
    }


    public int numberOfUsers(String filename) throws Exception {
        JSONObject jsonObject = (JSONObject) readJson(filename);
        JSONArray users = (JSONArray) jsonObject.get("users");
        return users.size();
    }

    // returns a JSON object that

    public JSONObject createSingleUserClassAssignment(String key1, Object value1, String key2, Object value2) {
        JSONObject singleUserClassAssignment = new JSONObject();
        singleUserClassAssignment.put(key1, value1);
        singleUserClassAssignment.put(key2, value2);
        return singleUserClassAssignment;
    }
    public JSONObject assignUsersToAssignments(String filename) throws Exception {

        JSONObject jsonObject = (JSONObject) readJson(filename);
        JSONArray users = (JSONArray) jsonObject.get("users");
        JSONArray classLabelAssignments = (JSONArray) jsonObject.get("class label assignments");

        JSONObject outputJSON = new JSONObject();

        JSONArray userClassAssignments = new JSONArray();

        for (int i = 0; i < users.size(); i++) {

            JSONObject user = (JSONObject) users.get(i);
            ArrayList<Integer> allClassLabelIDs = new ArrayList<Integer>();

            for (int j = 0; j < classLabelAssignments.size(); j++) {

                JSONObject assignment = (JSONObject) classLabelAssignments.get(j);
                JSONArray singleInstanceClassLabelIDs = (JSONArray) assignment.get("class label ids");

                if (user.get("user id") == assignment.get("user id")) {

                    for (int x = 0; x < singleInstanceClassLabelIDs.size(); x++) {
                        Long longLabelID = (Long) singleInstanceClassLabelIDs.get(x);
                        int labelID = longLabelID.intValue();
                        if (!(allClassLabelIDs.contains(labelID))) {
                            allClassLabelIDs.add(labelID);
                        }
                    }

                }
            }
            userClassAssignments.add(createSingleUserClassAssignment("user id", user.get("user id"), "class label ids",
                    allClassLabelIDs));
        }
        outputJSON.put("grouped class label assignments", userClassAssignments);

        return outputJSON;
    }

}
