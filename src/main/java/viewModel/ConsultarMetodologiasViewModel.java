package viewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Dependencies;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.CondicionTaxativa;
import model.metodologia.Metodologia;
import model.metodologia.condiciones.GreaterAndEqualThan;
import model.metodologia.condiciones.GreaterThan;
import model.metodologia.condiciones.LessThan;

public class ConsultarMetodologiasViewModel {
	
private BaseDeDatos baseDeDatos;
	
	private String nombreMetodologiaElegida = "";	
	

	private Metodologia metodologiaElegida;
	
		

	private List<Metodologia> listaMetodologias = new ArrayList<Metodologia>();	

	//private CondicionNoTaxativa criterioElegido;
	
	//private List<CondicionNoTaxativa> criterios = Arrays.asList();
	
	
	public ConsultarMetodologiasViewModel(String path) throws IOException{
		baseDeDatos = new BaseDeDatos(path);
		listaMetodologias.add(baseDeDatos.getBuffet());
	}
	
	@Dependencies("nombreMetodologiaElegida")
	public List<Metodologia> getMetodologias() throws IOException {
			if (nombreMetodologiaElegida.equals("")) {
				//return baseDeDatos.buscarMetodologias("");
			} else {		
				//return baseDeDatos.buscarMetodologias(nombreMetodologiaElegida);
			}
			
			return listaMetodologias;
	}
	/*
	@Dependencies("metodologiaElegida")
	public List<CondicionNoTaxativa> getCriterios() {
		if (this.metodologiaElegida == null) {
			
			return criterios;	
		} else {
			
			//criterios = this.metodologiaElegida.getCriterios();
			return this.metodologiaElegida.getCriterios();
		}
	}
	*/
	@Dependencies("metodologiaElegida")
	public List<Empresa> getEmpresas() throws IOException {
			if (metodologiaElegida == null) {
				
				return baseDeDatos.buscarEmpresas("");
			} else {		
				//return baseDeDatos.getEmpresas().filter();
				return metodologiaElegida.aplicarCondiciones(baseDeDatos.getEmpresas(), baseDeDatos);
			}
			
	}
	
	public Metodologia getMetodologiaElegida() {
		return metodologiaElegida;
	}

	public String getNombreMetodologiaElegida() {
		return metodologiaElegida.getNombre();
	}
/*
	public CondicionNoTaxativa getCriterioElegido() {
		return criterioElegido;
	}*/

	public void setMetodologiaElegida(Metodologia metodologiaElegida) {
		this.metodologiaElegida = metodologiaElegida;
	}
/*
	public void setCriterioElegido(CondicionNoTaxativa criterioElegido) {
		this.criterioElegido = criterioElegido;
	}*/
	
	public void setNombreMetodologiaElegida(String nombreMetodologiaElegida) {
		this.nombreMetodologiaElegida = nombreMetodologiaElegida;
	}
}
