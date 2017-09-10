package viewModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.CondicionTaxativa;
import model.metodologia.Metodologia;
import model.metodologia.NoTaxativaLongevidad;
import model.metodologia.TaxativaLongevidad;
import model.metodologia.condiciones.GreaterAndEqualThan;
import model.metodologia.condiciones.GreaterThan;
import model.metodologia.condiciones.LessThan;
import model.repositories.RepositorioDeMetodologias;

@Observable
public class ConsultarMetodologiasViewModel {
	
private BaseDeDatos baseDeDatos;
	
	private String nombreMetodologiaElegida = "";	
	private Metodologia metodologiaElegida;
	
	
	private List<Metodologia> metodologias = new ArrayList<Metodologia>();	

	private RepositorioDeMetodologias repositorio = RepositorioDeMetodologias.getInstance();
	
	
	public ConsultarMetodologiasViewModel(String path) throws IOException{
		baseDeDatos = new BaseDeDatos(path);
		baseDeDatos.leerEmpresas();
		baseDeDatos.leerIndicadores();
		
	}
	
	@Dependencies("nombreMetodologiaElegida")
	public List<Metodologia> getMetodologias() {
		return repositorio.filtrarPorNombre(nombreMetodologiaElegida);
			
	}
	
	@Dependencies("metodologiaElegida")
	public List<Empresa> getEmpresas() {
			if (metodologiaElegida == null) {
				
				return baseDeDatos.buscarEmpresas("");
			} else {		
				
				return metodologiaElegida.aplicarCondiciones(baseDeDatos.getEmpresas(), baseDeDatos);
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