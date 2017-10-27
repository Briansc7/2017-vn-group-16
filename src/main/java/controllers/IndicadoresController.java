package controllers;

import model.Indicador;
import model.Usuario;
import model.repositories.RepositorioDeIndicadores;
import parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class IndicadoresController implements WithGlobalEntityManager{

    RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();

    public ModelAndView listar(Request request, Response response) {

        Map<String, Object> model = new HashMap<>();
        if (request.cookie("userId") == null) {
            response.body("Debe iniciar sesion");
            response.redirect("/login");
            return null;
        }

        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
        List<Indicador> aux;

        if(request.queryParams("pagina") != null){
            int pagina = Integer.valueOf(request.queryParams("pagina"));
            aux = (List<Indicador>)(Object)repositorioDeIndicadores.buscarTodosPorUsuario(usuario).subList(pagina*1-1, pagina*1);
        } else
            aux = (List<Indicador>)(Object)repositorioDeIndicadores.buscarTodosPorUsuario(usuario).stream().limit(1).collect(Collectors.toList());

        model.put("indicadores", aux);
        return new ModelAndView(model, "indicadores.hbs");
    }

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
        if (request.cookie("userId") == null) {
            response.body("Debe iniciar sesion");
            response.redirect("/login");
            return null;
        }
        
        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));

        Indicador indicadorNuevo;// = null;
        try {
            indicadorNuevo = new Indicador(nombre, formula, usuario);
        } catch (ParseException e) {
            response.cookie("formulaIncorrecta", "si");
            //response.removeCookie("errorFormula");
            response.redirect("/indicadores/nuevo");
            return null;
        }

        repositorioDeIndicadores.guardar(indicadorNuevo);

        response.redirect("/indicadores");
        return null;
    }

}
