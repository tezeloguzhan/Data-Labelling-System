import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class LabelInstance extends JSONObject {


    public LabelInstance(User user, JSONArray assignedLabels, LabelDefinition labelDefinition) {
        super();
        this.put("instance id", labelDefinition.getId());
        this.put("class label ids", assignedLabels);
        this.put("user id", user.getId());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.put("datetime", dtf.format(now));
    }
}
