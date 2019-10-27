package com.example.api_good.restfulApis;

import com.google.gson.JsonObject;

public interface HandleResponse {

    void ResponseOK(JsonObject mainObject);

    void ResponseError(String errorMessage);

}
