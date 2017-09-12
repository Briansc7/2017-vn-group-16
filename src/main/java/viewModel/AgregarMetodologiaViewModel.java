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
import model.Indicador;
import model.metodologia.CondicionComparativa;
import model.metodologia.CondicionGeneral;
import model.metodologia.CondicionValorUnico;
import model.metodologia.Funcion;
import model.metodologia.Metodologia;
import model.metodologia.condiciones.Comparador;
import model.repositories.RepositorioDeMetodologias;
import parser.ParseException;
import parser.TokenMgrError;


@Observable
public class AgregarMetodologiaViewModel {

	private Integer periodoQueSeAnaliza;
	private Indicador indicador;
	private Funcion funcionAAplicar;
	private Comparador criterio;
	private String tipoAComparar;
	private BigDecimal valorContraElQueSeCompara;
	private String nombre;
	private BaseDeDatos baseDeDatos = new BaseDeDatos("");
	private RepositorioDeMetodologias repositorio = RepositorioDeMetodologias.getInstance();
	
	private List<CondicionGeneral> condiciones = new ArrayList<CondicionGeneral>();
	
	private List<Comparador> criterios;// = Arrays.asList(new EqualThan(), new GreaterThan(), new GreaterAndEqualThan(), new LessThan(), new LessAndEqualThan());
	private List<String> tiposParaComparar;// = Arrays.asList("constante", "indicador propio", "indicador de otra empresa");
	private List<Indicador> indicadores;// = new ArrayList<String>(baseDeDatos.getNombreIndicadores());
	
	public AgregarMetodologiaViewModel() throws ParseException, TokenMgrError {
		this.inicializarDatos();
	}
	
	private void inicializarDatos() throws ParseException, TokenMgrError {
		baseDeDatos.leerIndicadores();
		criterios = Arrays.asList(Comparador.IGUAL, Comparador.MAYOR, Comparador.MAYOROIGUAL, Comparador.MENOR, Comparador.MENOROIGUAL);
		tiposParaComparar = Arrays.asList("Constante", "Indicador de otra empresa");
		indicadores = new ArrayList<Indicador>(baseDeDatos.getIndicadores());
		indicadores.add(new Indicador("Longevidad", "1"));
		//AppData.getInstance().setInicializacionMetodologias(new PathFileTxtJson("./Archivos del sistema/Metodologias.txt"));
	}

	public void agregarCondicion() {
		
		if(periodoQueSeAnaliza == null){
			
			throw new CondicionIncompletaException("Falta ingresar el periodo.");
			
		} else if(indicador == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el indicador.");
			
		} else if(criterio == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el criterio.");
			
		} else if(tipoAComparar == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el tipo a comparar.");
			
		} else if(valorContraElQueSeCompara == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el valor a comparar.");
			
		} 
		
		if(tipoAComparar.equalsIgnoreCase("constante")){
			//if(indicador.seLlama("Longevidad"))
				this.condiciones.add(new CondicionValorUnico(periodoQueSeAnaliza, funcionAAplicar, criterio, valorContraElQueSeCompara));
			/*else
				this.condiciones.add(new CondicionValorUnico(new Integer(periodo), indicador, criterio, new BigDecimal(valor)));
			*/
		} else {
			this.condiciones.add(new CondicionComparativa(periodoQueSeAnaliza, funcionAAplicar, criterio));
		}
		/*if(tipoAComparar.equals("Indicador de otra empresa")) {
			if(indicador.equalsIgnoreCase("Longevidad"))
				this.condicionesNoTaxativas.add(new NoTaxativaLongevidad(periodoQueSeAnaliza, indicador, criterio, new Integer(valorContraElQueSeCompara)));
			else
				this.condicionesNoTaxativas.add(new CondicionNoTaxativa(periodoQueSeAnaliza, indicador, criterio, new Integer(valorContraElQueSeCompara)));
		} else {
			
			this.condiciones.add( new CondicionTaxativa(new Integer(periodoQueSeAnaliza), indicador, criterio, valorContraElQueSeCompara));
			
		}*/
		
	}

	public void agregarMetodologia() {

		if(nombre == null) {
			
			throw new MetodologiaSinNombreException("No se ingreso el nombre de la metodologia.");
			
		} else if(condiciones.isEmpty()) {
			
			throw new MetodologiaIncompletaException("No se agrego ninguna condicion.");
			
		}
		
		Metodologia unaMetodologia = new Metodologia(nombre, condiciones);
		
		repositorio.agregarMetodologia(unaMetodologia);
		
		
	}
	
	public Indicador getIndicador() {
		return indicador;
	}
	
	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public Comparador getCriterio() {
		return criterio;
	}

	public void setCriterio(Comparador criterio) {
		this.criterio = criterio;
	}

	public String getTipoAComparar() {
		return tipoAComparar;
	}

	public void setTipoAComparar(String tipoAComparar) {
		this.tipoAComparar = tipoAComparar;
	}
	
	public Integer getPeriodo() {
		return periodoQueSeAnaliza;
	}
	
	public void setPeriodo(Integer periodo) {
		this.periodoQueSeAnaliza = periodo;
	}
	
	public BigDecimal getValor() {
		return valorContraElQueSeCompara;
	}
	
	public void setValor(BigDecimal valorAComparar) {
		this.valorContraElQueSeCompara = valorAComparar;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Comparador> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Comparador> criterios) {
		this.criterios = criterios;
	}

	public List<String> getTiposParaComparar() {
		return tiposParaComparar;
	}

	public void setTiposParaComparar(List<String> tiposParaComparar) {
		this.tiposParaComparar = tiposParaComparar;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
	public List<CondicionGeneral> getCondicionesTaxativas() {
		return condiciones;
	}

	public void setCondicionesTaxativas(List<CondicionGeneral> condicionesTaxativas) {
		this.condiciones = condicionesTaxativas;
	}
}
