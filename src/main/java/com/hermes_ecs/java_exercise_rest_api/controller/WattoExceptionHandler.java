package com.hermes_ecs.java_exercise_rest_api.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


class WattoExceptionHandler {
    static final String EXCEPTION_ATTRIBUTE = "exception";
    private ModelAndView response;
    private ModelMap model;

    public WattoExceptionHandler(Throwable exception) {
        response = new ModelAndView();
        model = response.getModelMap();
        model.put(EXCEPTION_ATTRIBUTE, exception);
    }

    public ModelMap getModel() {
        return model;
    }

    public void setViewName(String viewName) {
        response.setViewName(viewName);
    }

    public ModelAndView getResponse() {
        return response;
    }
}
