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
import model.Metodologia;
import model.funciones.Consistencia;
import model.funciones.Funcion;
import model.funciones.Longevidad;
import model.funciones.Mediana;
import model.funciones.Promedio;
import model.funciones.Sumatoria;
import model.funciones.ValoresDelPeriodo;
import model.metodologia.condiciones.Comparador;
import model.metodologia.condiciones.CondicionComparativa;
import model.metodologia.condiciones.CondicionGeneral;
import model.metodologia.condiciones.CondicionValorUnico;
import model.repositories.RepositorioDeMetodologias;
import parser.ParseException;
import parser.TokenMgrError;

@Observable
public class AgregarMetodologiaViewModel {

	private Integer periodoQueSeAnaliza;
	private Indicador indicador;
	private Funcion funcionElegida;
	private Comparador criterio;
	private String tipoAComparar;
	private BigDecimal valorContraElQueSeCompara;
	private String nombreMetodologia;
	private BaseDeDatos baseDeDatos = new BaseDeDatos("");
	private RepositorioDeMetodologias repositorio = RepositorioDeMetodologias.getInstance();
	
	private List<CondicionGeneral> condiciones = new ArrayList<CondicionGeneral>();
	private List<Comparador> criterios;// = Arrays.asList(new EqualThan(), new GreaterThan(), new GreaterAndEqualThan(), new LessThan(), new LessAndEqualThan());
	private List<String> tiposParaComparar;// = Arrays.asList("constante", "indicador propio", "indicador de otra empresa");
	private List<Indicador> indicadores;// = new ArrayList<String>(baseDeDatos.getNombreIndicadores());
	private List<Funcion> funciones;
	
	public AgregarMetodologiaViewModel() throws ParseException, TokenMgrError {
		this.inicializarDatos();
	}
	
	private void inicializarDatos() throws ParseException, TokenMgrError {
		baseDeDatos.leerIndicadores();
		criterios = Arrays.asList(Comparador.IGUAL, Comparador.MAYOR, Comparador.MAYOROIGUAL, Comparador.MENOR, Comparador.MENOROIGUAL);
		tiposParaComparar = Arrays.asList("Constante", "Indicador de otra empresa");
		indicadores = new ArrayList<Indicador>(baseDeDatos.getIndicadores());
		//indicadores.add(new Indicador("Longevidad", "1"));
		funciones = Arrays.asList(new Consistencia(indicador), new Longevidad(), new Promedio(indicador), new ValoresDelPeriodo(indicador), new Sumatoria(indicador), new Mediana(indicador));
		//AppData.getInstance().setInicializacionMetodologias(new PathFileTxtJson("./Archivos del sistema/Metodologias.txt"));
	}

	public void agregarCondicion() {
		verificarQueNoHayaCamposVacios();
		
		if(tipoAComparar.equalsIgnoreCase("constante")){
			if(funcionElegida.seLlama("Longevidad")){
				condiciones.add(new CondicionValorUnico(periodoQueSeAnaliza, funcionElegida, criterio, valorContraElQueSeCompara));
			} else {
				funcionElegida.setIndicador(indicador);
				condiciones.add(new CondicionValorUnico(periodoQueSeAnaliza, funcionElegida, criterio, valorContraElQueSeCompara));
			}
		} else if(funcionElegida.seLlama("Longevidad")){
			condiciones.add(new CondicionComparativa(periodoQueSeAnaliza, funcionElegida, criterio));
		} else {
			funcionElegida.setIndicador(indicador);
			condiciones.add(new CondicionComparativa(periodoQueSeAnaliza, funcionElegida, criterio));
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

		if(nombreMetodologia.equals("")) {
			
			throw new MetodologiaSinNombreException("No se ingreso el nombre de la metodologia.");
			
		} else if(condiciones.isEmpty()) {
			
			throw new MetodologiaIncompletaException("No se agrego ninguna condicion.");
			
		}
		
		Metodologia unaMetodologia = new Metodologia(nombreMetodologia, condiciones);
		
		repositorio.agregarMetodologia(unaMetodologia);
	}
	
	private void verificarQueNoHayaCamposVacios(){
		if(periodoQueSeAnaliza == null){
			
			throw new CondicionIncompletaException("Falta ingresar el periodo.");
			
		} else if(indicador == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el indicador.");
			
		} else if(criterio == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el criterio.");
			
		} else if(funcionElegida == null) {
			
			throw new CondicionIncompletaException("Falta elegir la funcion.");
			
		} else if(tipoAComparar == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el tipo a comparar.");
			
		} else if(tipoAComparar == "Constante" && valorContraElQueSeCompara == null) {
			
			throw new CondicionIncompletaException("Falta ingresar el valor a comparar.");
			
		} 
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
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	
	public void setNombreMetodologia(String nombre) {
		this.nombreMetodologia = nombre;
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
	
	public List<CondicionGeneral> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<CondicionGeneral> condiciones) {
		this.condiciones = condiciones;
	}
	
	public Funcion getFuncionElegida() {
		return funcionElegida;
	}

	public void setFuncionElegida(Funcion funcionElegida) {
		this.funcionElegida = funcionElegida;
	}

	public List<Funcion> getFunciones() {
		return funciones;
	}
}
