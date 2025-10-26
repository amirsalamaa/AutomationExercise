package com.AutomationExercise.utils.dataReader;

import com.AutomationExercise.utils.logs.LogsManager;
import com.jayway.jsonpath.JsonPath;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.FileReader;

public class JsonReader {


    String jsonReader;
        String jsonFileName;

        public JsonReader(String jsonFileName) {
            this.jsonFileName = jsonFileName;
            try {
                String TEST_DATA_PATH = "src/test/resources/test-data/";
                JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
                jsonReader = data.toJSONString();
            } catch (Exception e) {
                LogsManager.error("Error reading json file:", jsonFileName, e.getMessage());
                jsonReader = "{}"; // Initialize to an empty JSON object to avoid null pointer exceptions
            }
        }

        //valid.username
        public String getJsonData(String jsonPath) {
            try {
                return JsonPath.read(jsonReader, jsonPath);
            } catch (Exception e) {
                LogsManager.error("Error reading json file for path:", jsonPath, e.getMessage());
                return "";
            }
        }

}
