package Utils;

import io.restassured.path.json.JsonPath;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonFileManager {

    private final String jsonFilePath;
    private FileReader reader;

    public JsonFileManager(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        try {
            reader = new FileReader(jsonFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param jsonPath  json Path
     * @return test data value from JSON file
     */
    public String getTestData(String jsonPath) {
        Object testData;
        try {
            reader = new FileReader(jsonFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        testData = JsonPath.from(reader).getString(jsonPath);
        return String.valueOf(testData);
    }
}

