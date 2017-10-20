package com.hermes_ecs.java_exercise_rest_api.controller;

public class RequestError {

    private final String message;

    public RequestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
