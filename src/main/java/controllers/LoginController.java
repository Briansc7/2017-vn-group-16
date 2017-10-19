package controllers;

import model.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

public class LoginController implements WithGlobalEntityManager {

    public ModelAndView mostrar(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        if(request.cookie("errorLogin") != null)
            model.put("errorLogin", true);
        else
            model.put("errorLogin", false);

        response.removeCookie("errorLogin");
        return new ModelAndView(model, "login.hbs");
    }

    public ModelAndView loguear(Request request, Response response) {
        Usuario usuario = null;
        //response.removeCookie("errorLogin");
        try{
            usuario = this.buscarUsuario(request.queryParams("username"));
        } catch (NoResultException e){
            response.cookie("errorLogin", "si");
            response.redirect("/login");
            return null;
        }
        //Usuario usuario = entityManager().find(Usuario.class, userId);
        if (!usuario.getPassword().equals(request.queryParams("password"))) {
            response.cookie("errorLogin", "si");
            response.redirect("/login");
            return null;
        }

        response.cookie("userId", String.valueOf(usuario.getId()));

        response.redirect("/");
        return null;
    }

    public ModelAndView logout(Request request, Response response){
        response.removeCookie("errorLogin");
        response.removeCookie("userId");
        response.redirect("/");
        return null;
    }

    private Usuario buscarUsuario(String username) {
        return entityManager()
                .createQuery("from Usuario as usuario where usuario.username = :username", Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
