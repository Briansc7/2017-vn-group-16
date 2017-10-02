package controllers;

import model.Empresa;
import model.repositories.RepositorioDeEmpresas;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

public class EmpresasController implements WithGlobalEntityManager, TransactionalOps {

    private RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();

    public ModelAndView listar(Request request, Response response) {
        List<Empresa> empresas;

        String filtroNombre = request.queryParams("filtroNombre");
        if (Objects.isNull(filtroNombre) || filtroNombre.isEmpty()) {
            empresas = repositorioDeEmpresas.obtenerEmpresas();
        } else {
            empresas = Arrays.asList(repositorioDeEmpresas.obtenerEmpresa(filtroNombre));
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("empresas", empresas);
        viewModel.put("filtroNombre", filtroNombre);

        return new ModelAndView(viewModel, "empresas.hbs");
    }

    public ModelAndView periododDe(Request request, Response response) {
        long id = Long.parseLong(request.params(":id"));

        Empresa empresa = repositorioDeEmpresas.buscar(id);

//        Map<String, Object> viewModel = new HashMap<>();
//        viewModel.put("periodos", empresa.getPeriodos());
//        viewModel.put("")
        return new ModelAndView(empresa, "periodos.hbs");
    }
}
