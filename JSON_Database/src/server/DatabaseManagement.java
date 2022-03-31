package server;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseManagement {

    Map<String, String> database;

    public DatabaseManagement(Map<String, String> database) {
        this.database = database;
    }

    Map<String, String> response = new LinkedHashMap<>();

    Map<String, String> setData(String key, String value) {

        response.clear();

        database.put(key, value);
        response.put("response", "OK");
        return response;
    }

    Map<String, String> getData(String key) {

        response.clear();

        if (database.containsKey(key)) {
            response.put("response", "OK");
            response.put("value", database.get(key));
            return response;
        } else {
            response.put("response", "ERROR");
            response.put("reason", "No such key");
            return response;
        }
    }

    Map<String, String> deleteData(String key) {

        response.clear();

        if (database.containsKey(key)) {
            database.remove(key);
            response.put("response", "OK");
            return response;
        } else {
            response.put("response", "ERROR");
            response.put("reason", "No such key");
            return response;
        }
    }
}
