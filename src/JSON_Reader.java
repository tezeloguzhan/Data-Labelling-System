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

public class JSON_Reader extends JSONArray {

    String availibilityStatus;

    public JSON_Reader(String availibilityStatus) {
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

    public static Object readJson(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }


    public static void writeJson(String filename, JSONObject jo) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jo.toJSONString());
        String prettyJsonString = gson.toJson(je);
        Files.write(Paths.get(filename), prettyJsonString.getBytes());
    }

    public int numberOfUsers(String filename) throws Exception {
        JSONObject jsonObject = (JSONObject) readJson(filename);
        JSONArray users = (JSONArray) jsonObject.get("users");
        return users.size();
    }

    public static JSONObject createSingleUserClassAssignment(String key1, Object value1, String key2, Object value2) {
        JSONObject singleUserClassAssignment = new JSONObject();
        singleUserClassAssignment.put(key1, value1);
        singleUserClassAssignment.put(key2, value2);
        return singleUserClassAssignment;
    }



}
