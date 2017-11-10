package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Empresa;
import model.Metodologia;
import model.Usuario;
import model.repositories.RepositorioDeEmpresas;
import model.repositories.RepositorioDeMetodologias;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController implements WithGlobalEntityManager, ControllerGeneral {

	private RepositorioDeMetodologias repositorioDeMetodologias = RepositorioDeMetodologias.getInstance();
	private RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();
	
    public ModelAndView listar(Request request, Response response) {
        List<Metodologia> metodologias;
        
        if (request.cookie("userId") == null) {
            response.redirect("/login");
            return null;
        }
        
        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
        String filtroNombre = request.queryParams("filtroNombre");

        if (Objects.isNull(filtroNombre) || filtroNombre.isEmpty()) {
            metodologias = repositorioDeMetodologias.buscarTodosPorUsuario(usuario);
        } else {
            metodologias = repositorioDeMetodologias.buscarTodosPorNombre(filtroNombre);
        }

        HashMap<String, Object> model = new HashMap<>();
        model.put("metodologias", metodologias);
        model.put("filtroNombre", filtroNombre);
        this.verificarLogin(model, request);

        return new ModelAndView(model, "metodologias.hbs");
    }


    public ModelAndView empresasDe(Request request, Response response) {
        long id = Long.parseLong(request.params(":metodologia"));
        List<Empresa> empresas;
        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
        empresas = repositorioDeEmpresas.buscarTodos();
        Metodologia metodologia = repositorioDeMetodologias.buscarPorId(id);

        Map<String, Object> model = new HashMap<>();
        model.put("empresas", metodologia.aplicarCondiciones(empresas));
        model.put("miMetodologia", metodologia.getNombre());
        this.verificarLogin(model, request);

        return new ModelAndView(model, "empresasPorMetodologia.hbs");
    }
}
