import java.io.FileWriter;
import java.util.*;

import org.json.simple.*;

public class Main {


    public static void main(String[] args) throws Exception {
        String filename = "users.json";
        JSONObject jsonObject = (JSONObject) JSON.readJson(filename);
        JSONArray users = (JSONArray) jsonObject.get("users");
        filename = "label dataset.json";
        jsonObject = (JSONObject) JSON.readJson(filename);
        jsonObject.put("users", users.clone());
        ArrayList<User> userList = new ArrayList<User>();
        Random r = new Random();
        for(int i = 0; i < 3; i ++) {
            int numOfUsers = users.size();
            int index = r.nextInt(numOfUsers);
            JSONObject user = (JSONObject) users.remove(index);
            userList.add(new User((Long)user.get("user_id"), (String)user.get("user_name"), (String)user.get("user_type")));
        }
        //Long datasetId = (Long)jsonObject.get("dataset id");
        //String datasetName = (String)jsonObject.get("dataset name");
        Long maxLabel = (Long)jsonObject.get("maximum number of labels per instance");
        int max = maxLabel.intValue();
        JSONArray labels = (JSONArray) jsonObject.get("class labels");
        JSONArray instances = (JSONArray) jsonObject.get("instances");
        LabelVector labelVector = new LabelVector();
        for(int i = 0; i < instances.size(); i ++) {
            int labelCount = r.nextInt(max + 1);
            ArrayList<Integer> labelIDs = new ArrayList<Integer>();
            for(int j = 0; j < labels.size(); j ++) {
                JSONObject obj = (JSONObject)labels.get(j);
                Long label_id = (Long) obj.get("label id");
                labelIDs.add(label_id.intValue());
            }
            JSONArray assignedLabels = new JSONArray();
            for(int j = 0; j < labelCount; j ++) {
                int index = r.nextInt(labelIDs.size());
                assignedLabels.add(labelIDs.remove(index));
            }
            assignedLabels.sort(null);
            User user = userList.get(r.nextInt(userList.size()));
            JSONObject instance = (JSONObject) instances.get(i);
            LabelDefinition labelDefinition = new LabelDefinition((Long)instance.get("id"), (String)instance.get("instance"));
            LabelInstance labelInstance = new LabelInstance(user, assignedLabels, labelDefinition);
            labelVector.add(labelInstance);
        }
        jsonObject.put("class label assignments", labelVector);
        FileWriter file = new FileWriter("output.json");
        file.write(jsonObject.toJSONString());
        file.flush();
        file.close();
    }

}
