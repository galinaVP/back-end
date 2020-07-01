package com.brainacad;

import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class JsonUtils {

    public static String stringFromJSONByPath(String json, String jsonPath) {
        String string = JsonPath.read(json, jsonPath).toString();
        return string;
    }

    public static int intFromJSONByPath(String json, String jsonPath) {
        int number = JsonPath.read(json, jsonPath);
        return number;
    }

    public static double doubleFromJSONByPath(String json, String jsonPath) {
        double doubleNumber = JsonPath.read(json, jsonPath);
        return doubleNumber;
    }

    public static List<String> listFromJSONByPath(String json, String jsonPath) {
        List<String> list = JsonPath.parse(json).read(jsonPath);
        return list;
    }

}
