import java.io.*;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonRead {

    public JSONObject JsonRead() throws IOException {
        String jsonString = "";
        File jsonFile = new File("vul.json");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(jsonFile),"UTF-8");
        String fileString = "";
        BufferedReader bufferedReader = new BufferedReader(reader);
        while ((fileString = bufferedReader.readLine())!= null){
            jsonString = jsonString + fileString;
        }
        JSONObject jsonObject = new JSONObject(jsonString);

        JSONArray jsonArray = new JSONArray();
        jsonArray = jsonObject.getJSONArray("Vulnerabilities");
        return jsonArray.getJSONObject(0);
    }
}
