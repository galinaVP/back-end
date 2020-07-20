package com;

import io.restassured.response.ValidatableResponse;

public class Context {
    private static Context instance;
    private ValidatableResponse response;

    private Context() {
    }

    public static Context getContext() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public ValidatableResponse getResponse() {
        return response;
    }

    public void setResponse(ValidatableResponse response) {
        this.response = response;
    }

    public void destroyContext() {
        instance = null;
    }
}
