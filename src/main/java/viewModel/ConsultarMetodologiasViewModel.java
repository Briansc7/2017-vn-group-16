package viewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.Indicador;
import model.Metodologia;
import model.repositories.RepositorioDeEmpresas;
import model.repositories.RepositorioDeIndicadores;
import model.repositories.RepositorioDeMetodologias;

@Observable
public class ConsultarMetodologiasViewModel {
	
	
	private String nombreMetodologiaElegida = "";	
	private Metodologia metodologiaElegida;
	
	private List<Empresa> empresas= new ArrayList<Empresa>();
	private List<Indicador> indicadores= new ArrayList<Indicador>();
	
	private List<Metodologia> metodologias = new ArrayList<Metodologia>();	

	//private RepositorioDeMetodologias repositorio = RepositorioDeMetodologias.getInstance();
	
	
	public ConsultarMetodologiasViewModel() throws IOException{
		//baseDeDatos = new BaseDeDatos(path);
		//baseDeDatos.leerEmpresas();
		//baseDeDatos.leerIndicadores();
		
		RepositorioDeEmpresas repositorioDeEmpresas = RepositorioDeEmpresas.getInstance();
		this.setEmpresas(repositorioDeEmpresas.buscarTodos());
		RepositorioDeIndicadores repositorioDeIndicadores = RepositorioDeIndicadores.getInstance();
		this.setIndicadores(repositorioDeIndicadores.buscarTodos());
		RepositorioDeMetodologias repositorioDeMetodologias = RepositorioDeMetodologias.getInstance();
		this.setMetodologias(repositorioDeMetodologias.buscarTodos());
	}
	
	@Dependencies("nombreMetodologiaElegida")
	public List<Metodologia> getMetodologias() {
		//return repositorio.buscarMetodologia(nombreMetodologiaElegida);
		if (nombreMetodologiaElegida.equals("")) {
			return this.buscarMetodologias("");
		} else {		
			return this.buscarMetodologias(nombreMetodologiaElegida);
		}	
	}
	
	@Dependencies("metodologiaElegida")
	public List<Empresa> getEmpresas() {
			if (metodologiaElegida == null) {
				
				return this.buscarEmpresas("");
			} else {		
				
				return metodologiaElegida.aplicarCondiciones(this.empresas);
			}
	}
	
	public Metodologia getMetodologiaElegida() {
		return metodologiaElegida;
	}

	public String getNombreMetodologiaElegida() {
		return nombreMetodologiaElegida;
	}


	public void setMetodologiaElegida(Metodologia metodologiaElegida) {
		this.metodologiaElegida = metodologiaElegida;
	}

	
	public void setNombreMetodologiaElegida(String nombreMetodologiaElegida) {
		this.nombreMetodologiaElegida = nombreMetodologiaElegida;
	}
	
	public List<Empresa> buscarEmpresas(String nombre) /*throws IOException */{
		return this.empresas.stream()
				.filter(empresa -> empresa.getNombre().toUpperCase().contains(nombre.toUpperCase()))
				.collect(Collectors.toList());
	}
	
	public List<Metodologia> buscarMetodologias(String nombre) /*throws IOException */{
		return this.metodologias.stream()
				.filter(metodologia -> metodologia.getNombre().toUpperCase().contains(nombre.toUpperCase()))
				.collect(Collectors.toList());
	}
	
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}
}


//hacer a las condiciones POLIMORFICAS. Tener una unica lista de condiciones
//Al aplicar la condicion a la metodologia te retorne la lista (mas chica u ordenada)
/*
probar taxativa y no taxativa -> usar mejores nombres porque genera confusion (algo para diferenciar que es filtro u orden)

test comparar que listas sean iguales. si a tiene los elementos de b, y b los de a, las listas son iguales

Importante crear un builder

no usar strings en lugar de usar los objetos.

tener en cuenta si pide periodo de 10 año y tiene 3 años o tiene huecos (se olvidó de cargar 2016).

Customizar la lista de empresas para decir si conviene invertir, el orden, si no aplica, etc

condicion conozca a funcion promedio, reciba una empresa, un indicador y la cantidad de periodos.
roe de los ultimos 5 periodos, ejemplo que haga sumatoria (funcion que actua sobre el indicador).
funcion va a devolver un solo valor que se lo aplica a condicion.



tarea
diagrama de clases completo

no guardar como strings las cosas, trabajar con la referencia exacta

una sola lista para las metodologias. Interfaz que entienda los mismos metodos al aplicar la condicion a la metodologia.
interfaz va a servir para cuando hagas una lista hagas una lista de esa interfaz. A todo lo que haya que hacer polimorfico lo mas prolijo es usar interfaz

separar test de operaciones, de metodologias, de condiciones, de funciones.

Usar builder para por ejemplo hacer mas chico las cosas en las metodologias o usar una abstraccion.

Al iniciar la aplicacion que se cargue automaticamente el archivo con las empresas

*/