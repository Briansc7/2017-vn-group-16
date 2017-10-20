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
import model.repositories.RepositorioDeIndicadores;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController implements WithGlobalEntityManager{
	private RepositorioDeMetodologias repositorioDeMetodologias = RepositorioDeMetodologias.getInstance();
	private RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();
	
    public ModelAndView listar(Request request, Response response) {
        List<Metodologia> metodologias;
        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));

        String filtroNombre = request.queryParams("filtroNombre");
        if (Objects.isNull(filtroNombre) || filtroNombre.isEmpty()) {
            metodologias = repositorioDeMetodologias.buscarTodosPorUsuario(usuario);//.buscarTodos();
        } else {
            //empresas = Arrays.asList(repositorioDeEmpresas.buscarEmpresa(filtroNombre));
            metodologias = repositorioDeMetodologias.buscarTodosPorNombre(filtroNombre);
        }

        HashMap<String, Object> viewModel = new HashMap<>();
        viewModel.put("metodologias", metodologias);
        viewModel.put("filtroNombre", filtroNombre);

        return new ModelAndView(viewModel, "metodologias.hbs");
    }


    public ModelAndView empresasDe(Request request, Response response) {
        long id = Long.parseLong(request.params(":metodologia"));
        List<Empresa> empresas;
        Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
        empresas = repositorioDeEmpresas.buscarTodos();
        
        Metodologia metodologia = repositorioDeMetodologias.buscarPorId(id);
        //Usuario usuario = entityManager().find(Usuario.class, Long.valueOf(request.cookie("userId")));
        
        Map<String, Object> viewModel = new HashMap<>();
        viewModel.put("empresas", metodologia.aplicarCondiciones(empresas));

        return new ModelAndView(viewModel, "empresas.hbs");
    }
}
