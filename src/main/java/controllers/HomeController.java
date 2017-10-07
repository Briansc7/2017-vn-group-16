package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController {

    public ModelAndView mostrar(Request request, Response response) {
        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("username", request.cookie("username"));

        return new ModelAndView(viewModel, "home.hbs");
    }
}
