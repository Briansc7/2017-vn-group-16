package controllers;

import model.Empresa;
import model.Usuario;
import model.repositories.RepositorioDeEmpresas;
import model.repositories.RepositorioDeIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class EmpresasController implements WithGlobalEntityManager {

    private RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();

    public ModelAndView listar(Request request, Response response) {
        List<Empresa> empresas;

        String filtroNombre = request.queryParams("filtroNombre");
        if (Objects.isNull(filtroNombre) || filtroNombre.isEmpty()) {
            empresas = repositorioDeEmpresas.buscarTodos();
        } else {
            //empresas = Arrays.asList(repositorioDeEmpresas.buscarEmpresa(filtroNombre));
            empresas = repositorioDeEmpresas.buscarTodosPorNombre(filtroNombre);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("empresas", empresas);
        viewModel.put("filtroNombre", filtroNombre);

        return new ModelAndView(viewModel, "empresas.hbs");
    }

    public ModelAndView periododDe(Request request, Response response) {
        long id = Long.parseLong(request.params(":id"));

        Empresa empresa = repositorioDeEmpresas.buscarPorId(id);

//        Map<String, Object> viewModel = new HashMap<>();
//        viewModel.put("periodos", empresa.getPeriodos());
//        viewModel.put("")
        return new ModelAndView(empresa, "periodos.hbs");
    }

    public ModelAndView atributosDe(Request request, Response response) {
        long id = Long.parseLong(request.params(":id"));
        int periodo = Integer.parseInt(request.params(":periodo"));
        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
       // long usuario_id = Long.valueOf(request.cookie("userId"));

        Empresa empresa = repositorioDeEmpresas.buscarPorId(id);

        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("cuentas", empresa.cuentasDelPeriodo(periodo));
        viewModel.put("empresa", empresa);
        viewModel.put("indicadores", RepositorioDeIndicadores.getInstance().getIndicadoresAuxiliares(empresa, periodo, usuario));

        return new ModelAndView(viewModel, "atributos.hbs");
    }
}
