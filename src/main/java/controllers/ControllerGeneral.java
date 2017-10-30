package controllers;

import spark.Request;

import java.util.Map;

public interface ControllerGeneral {
    default void verificarLogin(Map<String, Object> model, Request request){
        if(request.cookie("userId")!= null){
            model.put("logueado", true);
        } else {
            model.put("logueado", false);
        }
    }
}
