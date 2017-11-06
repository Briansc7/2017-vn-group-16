package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import model.repositories.RepositorioIndicadoresPrecalculados;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Empresa;
import model.Usuario;
import model.repositories.RepositorioDeEmpresas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EmpresasController implements WithGlobalEntityManager, ControllerGeneral {

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
        this.verificarLogin(viewModel, request);

        return new ModelAndView(viewModel, "empresas.hbs");
    }

    public ModelAndView periododDe(Request request, Response response) {
        long id = Long.parseLong(request.params(":id"));

        Map<String, Object> model = new HashMap<>();
        model.put("empresa", repositorioDeEmpresas.buscarPorId(id));
        this.verificarLogin(model, request);

        return new ModelAndView(model, "periodos.hbs");
    }

    public ModelAndView atributosDe(Request request, Response response) {
        long id = Long.parseLong(request.params(":id"));
        int periodo = Integer.parseInt(request.params(":periodo"));
        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
        Empresa empresa = repositorioDeEmpresas.buscarPorId(id);

        Map<String, Object> model = new HashMap<>();
        model.put("cuentas", empresa.cuentasDelPeriodo(periodo));
        model.put("empresa", empresa);
        model.put("indicadores", RepositorioIndicadoresPrecalculados.getInstance().buscarTodosFiltrados(empresa, periodo, usuario));
        this.verificarLogin(model, request);

        return new ModelAndView(model, "atributos.hbs");
    }
}
