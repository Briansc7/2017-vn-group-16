package viewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Dependencies;
import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;
import model.Cuenta;
import model.Empresa;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.CondicionTaxativa;
import model.metodologia.Metodologia;
import model.metodologia.condiciones.GreaterAndEqualThan;
import model.metodologia.condiciones.GreaterThan;
import model.metodologia.condiciones.LessThan;
import model.repositories.RepositorioDeMetodologias;

@Observable
public class ConsultarMetodologiasViewModel {
	
private BaseDeDatos baseDeDatos;
	
	private String nombreMetodologiaElegida = "";	
	private Metodologia metodologiaElegida;
	
	private CondicionNoTaxativa condicionRoe = new CondicionNoTaxativa(2, "ROE", new GreaterThan(), 1);
	private CondicionNoTaxativa condicionDeuda = new CondicionNoTaxativa(2, "debtEquityRatio", new LessThan(), 2);
	private CondicionTaxativa condicionMargen = new CondicionTaxativa(2, "Margen", new GreaterAndEqualThan(), "margen");
	private Metodologia buffet = new Metodologia("Buffet", Arrays.asList(condicionMargen), Arrays.asList(condicionRoe, condicionDeuda));	

	private List<Metodologia> listaMetodologias = new ArrayList<Metodologia>();	

	//private CondicionNoTaxativa criterioElegido;
	
	//private List<CondicionNoTaxativa> criterios = Arrays.asList();
	
	
	public ConsultarMetodologiasViewModel(String path) throws IOException{
		baseDeDatos = new BaseDeDatos(path);
		//listaMetodologias.add(buffet);
		RepositorioDeMetodologias.getInstance().agregarMetodologia(buffet);
	}
	
	@Dependencies("nombreMetodologiaElegida")
	public List<Metodologia> getMetodologias() {
			return RepositorioDeMetodologias.getInstance().filtrarPorNombre(nombreMetodologiaElegida);
			//return listaMetodologias;
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
		return nombreMetodologiaElegida;
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
