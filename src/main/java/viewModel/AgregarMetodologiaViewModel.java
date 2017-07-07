package viewModel;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import exceptions.CondicionIncompletaException;
import exceptions.MetodologiaIncompletaException;
import exceptions.MetodologiaSinNombreException;
import model.Indicador;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.CondicionTaxativa;
import model.metodologia.Metodologia;
import model.metodologia.condiciones.BooleanCondition;
import model.metodologia.condiciones.EqualThan;
import model.metodologia.condiciones.GreaterAndEqualThan;
import model.metodologia.condiciones.GreaterThan;
import model.metodologia.condiciones.LessAndEqualThan;
import model.metodologia.condiciones.LessThan;
import model.repositories.RepositorioDeMetodologias;


@Observable
public class AgregarMetodologiaViewModel {

	private String periodo;
	private String indicador;
	private BooleanCondition criterio;
	private BooleanCondition criterioReal;
	private String tipoAComparar;
	private String valorAComparar;
	private String nombre;
	
	private List<CondicionTaxativa> condicionesTaxativas = Arrays.asList();
	private List<CondicionNoTaxativa> condicionesNoTaxativas = Arrays.asList();
	
	private List<BooleanCondition> criterios = Arrays.asList(new EqualThan(), new GreaterThan(), new GreaterAndEqualThan(), new LessThan(), new LessAndEqualThan());
	private List<String> tiposParaComparar = Arrays.asList("constante", "indicador propio", "indicador de otra empresa");
	private List<String> indicadores = Arrays.asList("indicadorE");
	
	public AgregarMetodologiaViewModel(){
		
	}
	
	public void agregarCondicion() {
		
		if(periodo == null){
			
			throw new CondicionIncompletaException("Falta ingresar el periodo.");
			
		} else if(indicador == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el indicador.");
			
		} else if(criterio == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el criterio.");
			
		} else if(tipoAComparar == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el tipo a comparar.");
			
		} else if(valorAComparar == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el valor a comparar.");
			
		} 
		
		if(tipoAComparar == "constante"){
			
			this.condicionesTaxativas.add( new CondicionTaxativa(new Integer(periodo), indicador, criterio, new BigDecimal(valorAComparar)));
			
		} else if(tipoAComparar == "indicador propio") {
			
			this.condicionesTaxativas.add( new CondicionTaxativa(new Integer(periodo), indicador, criterio, valorAComparar));
			
		} else {
			
			this.condicionesNoTaxativas.add( new CondicionNoTaxativa(new Integer(periodo), indicador, criterio, new Integer(valorAComparar)));
			
		}
		
	}

	public void agregarMetodologia() {

		if(nombre == null) {
			
			throw new MetodologiaSinNombreException("No se ingreso el nombre de la metodologia.");
			
		} else if(condicionesTaxativas.isEmpty() && condicionesNoTaxativas.isEmpty()) {
			
			throw new MetodologiaIncompletaException("No se agrego ninguna condicion.");
			
		}
		
		Metodologia unaMetodologia = new Metodologia(nombre, condicionesTaxativas, condicionesNoTaxativas);
		
		RepositorioDeMetodologias.getInstance().agregarMetodologia(unaMetodologia);
		
	}
	
	public String getIndicador() {
		return indicador;
	}
	
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public BooleanCondition getCriterio() {
		return criterio;
	}

	public void setCriterio(BooleanCondition criterio) {
		this.criterio = criterio;
	}

	public String getTipoAComparar() {
		return tipoAComparar;
	}

	public void setTipoAComparar(String tipoAComparar) {
		this.tipoAComparar = tipoAComparar;
	}
	
	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getValorAComparar() {
		return valorAComparar;
	}
	
	public void setValorAComparar(String valorAComparar) {
		this.valorAComparar = valorAComparar;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<BooleanCondition> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<BooleanCondition> criterios) {
		this.criterios = criterios;
	}

	public List<String> getTiposParaComparar() {
		return tiposParaComparar;
	}

	public void setTiposParaComparar(List<String> tiposParaComparar) {
		this.tiposParaComparar = tiposParaComparar;
	}

	public List<String> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<String> indicadores) {
		this.indicadores = indicadores;
	}
	
}
