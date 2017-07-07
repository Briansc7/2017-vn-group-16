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
