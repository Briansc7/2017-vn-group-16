package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
    public ModelAndView mostrar(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView loguear(Request request, Response response) {
        response.cookie("username", request.queryParams("username"));
        response.redirect("/");

        return null;
    }
}
