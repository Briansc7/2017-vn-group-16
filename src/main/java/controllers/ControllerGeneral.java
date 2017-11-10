package controllers;

import spark.Request;

import java.util.Map;

import model.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public interface ControllerGeneral extends WithGlobalEntityManager{
    default void verificarLogin(Map<String, Object> model, Request request){
        if(request.cookie("userId")!= null){
            model.put("logueado", true);
            Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
            model.put("username", usuario.getUsername());
        } else {
            model.put("logueado", false);
        }
    }
}
