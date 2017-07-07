package viewModel;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import exceptions.CondicionIncompletaException;
import exceptions.MetodologiaIncompletaException;
import exceptions.MetodologiaSinNombreException;
import model.BaseDeDatos;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.CondicionTaxativa;
import model.metodologia.Metodologia;
import model.metodologia.NoTaxativaLongevidad;
import model.metodologia.TaxativaLongevidad;
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
	private String tipoAComparar;
	private String valor;
	private String nombre;
	private BaseDeDatos baseDeDatos = new BaseDeDatos("");
	
	private List<CondicionTaxativa> condicionesTaxativas = new ArrayList<CondicionTaxativa>();
	private List<CondicionNoTaxativa> condicionesNoTaxativas = new ArrayList<CondicionNoTaxativa>();
	
	private List<BooleanCondition> criterios;// = Arrays.asList(new EqualThan(), new GreaterThan(), new GreaterAndEqualThan(), new LessThan(), new LessAndEqualThan());
	private List<String> tiposParaComparar;// = Arrays.asList("constante", "indicador propio", "indicador de otra empresa");
	private List<String> indicadores;// = new ArrayList<String>(baseDeDatos.getNombreIndicadores());
	
	public AgregarMetodologiaViewModel() {
		this.inicializarDatos();
	}
	
	private void inicializarDatos() {
		baseDeDatos.leerIndicadores();
		criterios = Arrays.asList(new EqualThan(), new GreaterThan(), new GreaterAndEqualThan(), new LessThan(), new LessAndEqualThan());
		tiposParaComparar = Arrays.asList("Constante", "Indicador propio", "Indicador de otra empresa");
		indicadores = new ArrayList<String>(baseDeDatos.getNombreIndicadores());
		indicadores.add("Longevidad");
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
			
		} else if(valor == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el valor a comparar.");
			
		} 
		
		if(tipoAComparar.equalsIgnoreCase("constante")){
			if(indicador.equalsIgnoreCase("Longevidad"))
				this.condicionesTaxativas.add(new TaxativaLongevidad(new Integer(periodo), indicador, criterio, new BigDecimal(valor)));
			else
				this.condicionesTaxativas.add(new CondicionTaxativa(new Integer(periodo), indicador, criterio, new BigDecimal(valor)));
			
		} else if(tipoAComparar.equals("Indicador de otra empresa")) {
			if(indicador.equalsIgnoreCase("Longevidad"))
				this.condicionesNoTaxativas.add(new NoTaxativaLongevidad(new Integer(periodo), indicador, criterio, new Integer(valor)));
			else
				this.condicionesNoTaxativas.add(new CondicionNoTaxativa(new Integer(periodo), indicador, criterio, new Integer(valor)));
		} else {
			
			this.condicionesTaxativas.add( new CondicionTaxativa(new Integer(periodo), indicador, criterio, valor));
			
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
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valorAComparar) {
		this.valor = valorAComparar;
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
	
	public List<CondicionTaxativa> getCondicionesTaxativas() {
		return condicionesTaxativas;
	}

	public void setCondicionesTaxativas(List<CondicionTaxativa> condicionesTaxativas) {
		this.condicionesTaxativas = condicionesTaxativas;
	}

	public List<CondicionNoTaxativa> getCondicionesNoTaxativas() {
		return condicionesNoTaxativas;
	}

	public void setCondicionesNoTaxativas(List<CondicionNoTaxativa> condicionesNoTaxativas) {
		this.condicionesNoTaxativas = condicionesNoTaxativas;
	}
}
