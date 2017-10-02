package controllers;

import model.Empresa;
import model.repositories.RepositorioDeEmpresas;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EmpresasController implements WithGlobalEntityManager, TransactionalOps {
    public ModelAndView listar(Request request, Response response) {
        List<Empresa> empresas;

        String filtroNombre = request.queryParams("filtroNombre");
        if (Objects.isNull(filtroNombre) || filtroNombre.isEmpty()) {
            empresas = RepositorioDeEmpresas.getInstance().obtenerEmpresas();
        } else {
            empresas = Arrays.asList(RepositorioDeEmpresas.getInstance().obtenerEmpresa(filtroNombre));
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("empresas", empresas);
        viewModel.put("filtroNombre", filtroNombre);

        return new ModelAndView(viewModel, "empresas.hbs");
    }
}
