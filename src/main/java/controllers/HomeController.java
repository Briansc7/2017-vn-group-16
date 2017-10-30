package controllers;

import model.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController implements WithGlobalEntityManager, ControllerGeneral {

    public ModelAndView mostrar(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        if(request.cookie("userId")!= null){
            Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
            model.put("username", usuario.getUsername());
        }
        this.verificarLogin(model, request);

        return new ModelAndView(model, "home.hbs");
    }
}
