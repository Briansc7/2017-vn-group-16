package controllers;

import model.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class LoginController implements WithGlobalEntityManager {

    public ModelAndView mostrar(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView loguear(Request request, Response response) {
        Usuario usuario = null;
        try{
            usuario = this.buscarUsuario(request.queryParams("username"));
        } catch (NoResultException e){
            response.redirect("/loginIncorrecto");
        }
        //Usuario usuario = entityManager().find(Usuario.class, userId);
        if (!usuario.getPassword().equals(request.queryParams("password")))
            response.redirect("/loginIncorrecto");

        response.cookie("userId", String.valueOf(usuario.getId()));

        response.redirect("/");
        return null;
    }

    public ModelAndView logout(Request request, Response response){
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
