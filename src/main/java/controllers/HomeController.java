package controllers;

import model.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController implements WithGlobalEntityManager {

    public ModelAndView mostrar(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
//        viewModel.put("userId", request.cookie("userId"));

        if(request.cookie("userId")!= null){
            Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
            viewModel.put("username", usuario.getUsername());
        }

        return new ModelAndView(viewModel, "home.hbs");
    }
}
