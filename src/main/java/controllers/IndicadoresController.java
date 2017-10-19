package controllers;

import model.Indicador;
import model.repositories.RepositorioDeIndicadores;
import parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class IndicadoresController {

    RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();

    public ModelAndView nuevo(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        if(request.cookie("formulaIncorrecta") != null)
            model.put("errorFormula", true);
        else
            model.put("errorFormula", false);

        response.removeCookie("formulaIncorrecta");
        return new ModelAndView(model, "indicador-nuevo.hbs");
    }

    public ModelAndView agregar(Request request, Response response) {
        String nombre = request.queryParams("nombre");
        String formula = request.queryParams("formula");
        //response.removeCookie("errorFormula");

        Indicador indicadorNuevo = null;
        try {
            indicadorNuevo = new Indicador(nombre, formula);
        } catch (ParseException e) {
            response.cookie("formulaIncorrecta", "si");
            //response.removeCookie("errorFormula");
            response.redirect("/indicadores/nuevo");
            return null;
        }

        repositorioDeIndicadores.guardar(indicadorNuevo);

        response.redirect("/");
        return null;
    }
}
