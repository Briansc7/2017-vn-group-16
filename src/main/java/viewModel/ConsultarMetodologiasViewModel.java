package viewModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Dependencies;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.Metodologia;

public class ConsultarMetodologiasViewModel {
	
private BaseDeDatos baseDeDatos;
	
	private String nombreMetodologiaElegida = "";	
	private Metodologia metodologiaElegida;
	
	

	private CondicionNoTaxativa criterioElegido;
	
	private List<String> criterios = Arrays.asList();
	
	
	public ConsultarMetodologiasViewModel(String path) throws IOException{
		
	}
	
	@Dependencies("nombreMetodologiaElegida")
	public List<Metodologia> getMetodologias() throws IOException {
			if (nombreMetodologiaElegida.equals("")) {
				//return baseDeDatos.buscarMetodologias("");
			} else {		
				//return baseDeDatos.buscarMetodologias(nombreMetodologiaElegida);
			}
			
			return null;
	}
	
	@Dependencies("metodologiaElegida")
	public List<String> getCriterios() {
		if (this.metodologiaElegida == null) {
			
			return criterios;	
		} else {
			
			//criterios = this.metodologiaElegida.getCriterios();
			return criterios;
		}
	}
	
	@Dependencies("criterioElegido")
	public List<Empresa> getEmpresas() throws IOException {
			if (criterioElegido == null) {
				
				//return baseDeDatos.buscarEmpresas("");
			} else {		
				//return baseDeDatos.buscarEmpresas(nombreEmpresaElegida);
			}
			
			return null;
	}
	
	public Metodologia getMetodologiaElegida() {
		return metodologiaElegida;
	}

	public String getNombreMetodologiaElegida() {
		return nombreMetodologiaElegida;
	}

	public CondicionNoTaxativa getCriterioElegido() {
		return criterioElegido;
	}

	public void setMetodologiaElegida(Metodologia metodologiaElegida) {
		this.metodologiaElegida = metodologiaElegida;
	}

	public void setCriterioElegido(CondicionNoTaxativa criterioElegido) {
		this.criterioElegido = criterioElegido;
	}
}
