package com.brainacad;

//import com.jayway.jsonpath.JsonPath;

import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class JsonUtils {

    public static String stringFromJSONByPath(String json, String jsonPath) {
        List<String> jsonStructure = JsonPath.read(json, jsonPath);
//        String string = JsonPath.read(json, jsonPath).toString();
        return jsonStructure.stream().findFirst().orElse("");
//        return string;
    }

    public static int intFromJSONByPath(String json, String jsonPath) {
        //int number = Integer.parseInt(JsonPath.read(json, jsonPath));
        //return number;
        List<Integer> jsonStructure = JsonPath.read(json, jsonPath);
        return jsonStructure.stream().findFirst().orElse(0);
    }

    public static double doubleFromJSONByPath(String json, String jsonPath) {
        //double doubleNumber = JsonPath.read(json, jsonPath);
       // return doubleNumber;
        List<Double> jsonStructure = JsonPath.read(json, jsonPath);
        return jsonStructure.stream().findFirst().orElse(0.0);
    }

    public static List<String> listFromJSONByPath(String json, String jsonPath) {
        List<String> list = JsonPath.parse(json).read(jsonPath);
        return list;
    }

}
