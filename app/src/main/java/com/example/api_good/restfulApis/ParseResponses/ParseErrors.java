package com.example.api_good.restfulApis.ParseResponses;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ParseErrors {

    public static String parseServerErrors(JsonObject object) {
        String error = "no errors";
        try {
            JsonArray errors = object.get("errors").getAsJsonArray();
            for (int i = 0; i < errors.size(); i++) {
                error = errors.get(i).getAsString();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return error;
    }

}
