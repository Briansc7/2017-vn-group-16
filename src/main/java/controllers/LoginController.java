package controllers;

import model.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityTransaction;

public class LoginController implements WithGlobalEntityManager {

    public ModelAndView mostrar(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView loguear(Request request, Response response) {
        response.cookie("username", request.queryParams("username"));
        response.redirect("/");

        String userId = String.valueOf(this.buscarIdDe(request.queryParams("username")));
        request.session(true);
        request.session().attribute("userId", userId);
        return null;
    }

    private Long buscarIdDe(String username) {
        return entityManager()
                .createQuery("from Usuarios as usuario where usuario.username = :username", Usuario.class)
                .setParameter("username", username)
                .getSingleResult()
                .getId();
    }
}
