package controllers;

import model.Indicador;
import model.Usuario;
import model.repositories.RepositorioDeIndicadores;
import parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class IndicadoresController implements WithGlobalEntityManager, ControllerGeneral {

    RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();

    public ModelAndView listar(Request request, Response response) {
        if (request.cookie("userId") == null) {
            response.redirect("/login");
            return null;
        }

        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
        Map<String, Object> model = new HashMap<>();
        model.put("indicadores", repositorioDeIndicadores.buscarTodosPorUsuario(usuario));
        this.verificarLogin(model, request);

        return new ModelAndView(model, "indicadores.hbs");
    }

    public ModelAndView nuevo(Request request, Response response) {
        if (request.cookie("userId") == null) {
            response.redirect("/login");
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        if(request.queryParams("formulaIncorrecta") != null)
            model.put("errorFormula", true);
        else
            model.put("errorFormula", false);

        this.verificarLogin(model, request);

        return new ModelAndView(model, "indicador-nuevo.hbs");
    }

    public ModelAndView agregar(Request request, Response response) {
        String nombre = request.queryParams("nombre");
        String formula = request.queryParams("formula");

        if (request.cookie("userId") == null) {
            response.redirect("/login");
            return null;
        }

        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
        Indicador indicadorNuevo;
        try {
            indicadorNuevo = new Indicador(nombre.toLowerCase(), formula, usuario);
        } catch (ParseException e) {
            //response.cookie("formulaIncorrecta", "si");
            response.redirect("/indicadores/nuevo?formulaIncorrecta=");
            return null;
        }

        repositorioDeIndicadores.guardar(indicadorNuevo);
        response.redirect("/indicadores");
        return null;
    }

}
