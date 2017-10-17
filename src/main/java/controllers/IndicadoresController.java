package controllers;

import model.Empresa;
import model.Indicador;
import model.repositories.RepositorioDeIndicadores;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import parser.ParseException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController implements WithGlobalEntityManager, TransactionalOps {

    RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();

    public ModelAndView nuevo(Request request, Response response) {
        return new ModelAndView(null, "indicador-nuevo.hbs");
    }

    public ModelAndView agregar(Request request, Response response) {
        String nombre = request.queryParams("nombre");
        String formula = request.queryParams("formula");

        withTransaction(() -> {
            try {
                repositorioDeIndicadores.guardar(new Indicador(nombre, formula));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        response.redirect("/");
        return null;
    }
}
