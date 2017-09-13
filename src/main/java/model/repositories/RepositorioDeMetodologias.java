package model.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseYaExisteException;
import mockObjects.AppDataI;
import model.Metodologia;
import model.funciones.Consistencia;
import model.funciones.Longevidad;
import model.funciones.ValorParaNAnios;
import model.metodologia.condiciones.Comparador;
import model.metodologia.condiciones.CondicionGeneral;
import testMetodologia.CondicionBuilder;
import utils.AppData;

public class RepositorioDeMetodologias implements WithGlobalEntityManager{
	private static RepositorioDeMetodologias instance;
	private List<Metodologia> metodologias = new ArrayList<Metodologia>();
	private AppDataI appData = AppData.getInstance();
	/*
	//private CondicionGeneral condicionRoe = new CondicionNoTaxativa(2, "ROE", new GreaterThan(), 1);
	private CondicionGeneral condicionRoe = new CondicionBuilder()
			.periodoDeEvaluacion(2)
			.funcionParaObtenerValor(new ValorParaNAnios(base.buscarIndicador("ROE")))
			.comparador(Comparador.MAYOR)
			.build();
	//private CondicionNoTaxativa condicionDeuda = new CondicionNoTaxativa(2, "debtEquityRatio", new LessThan(), 2);
	private CondicionGeneral condicionDeuda = new CondicionBuilder()
			.periodoDeEvaluacion(2)
			.funcionParaObtenerValor(new ValorParaNAnios(base.buscarIndicador("debtEquityRatio")))
			.comparador(Comparador.MENOR)
			.build();
	//private CondicionTaxativa condicionMargen = new CondicionTaxativa(2, "Margen", new GreaterAndEqualThan(), "margen");
	private CondicionGeneral condicionMargen = new CondicionBuilder()
			.periodoDeEvaluacion(2)
			.funcionParaObtenerValor(new Consistencia(base.buscarIndicador("margen")))
			.comparador(Comparador.MAYOROIGUAL)
			.build();
	//private TaxativaLongevidad condicionLongevidad1 = new TaxativaLongevidad(0, "longevidad", new GreaterAndEqualThan(), new BigDecimal(2));
	private CondicionGeneral condicionLongevidadPropia = new CondicionBuilder()
			.periodoDeEvaluacion(1)
			.funcionParaObtenerValor(new Longevidad())
			.comparador(Comparador.MAYOROIGUAL)
			.valor(new BigDecimal(2))
			.build();
	//private NoTaxativaLongevidad condicionLongevidad2 = new NoTaxativaLongevidad(0, "longevidad", new GreaterThan(), 5);
	private CondicionGeneral condicionLongevidadComparativa = new CondicionBuilder()
			.periodoDeEvaluacion(1)
			.funcionParaObtenerValor(new Longevidad())
			.comparador(Comparador.MAYOR)
			.valor(new BigDecimal(5))
			.build();
	private Metodologia buffet = new Metodologia("Buffet", Arrays.asList(condicionMargen, condicionLongevidadPropia, condicionRoe, condicionDeuda, condicionLongevidadComparativa));	
	*/
	//Singleton
	private RepositorioDeMetodologias(){
		//metodologias.add(buffet);
		archivarRepositorio();
	}
	
	public synchronized static RepositorioDeMetodologias getInstance(){
		if(instance == null)
			instance = new RepositorioDeMetodologias();
		return instance;
	}
	
	public void inicializar(List<Metodologia> _metodologias){
		metodologias.addAll(_metodologias);
	}

	public void agregarMetodologias(List<Metodologia> _metodologias) {
		_metodologias.stream().forEach(metodologia -> agregarMetodologia(metodologia));
	}
	
	public void agregarMetodologia(Metodologia metodologia) {
		comprobarNombre(metodologia);
		metodologias.add(metodologia);
		//archivarRepositorio();
	}

	public void removerMetodologia(Metodologia metodologia) {
			metodologias.remove(metodologia);
			archivarRepositorio();
	}
	
	// Filtrar metodologias del repositorio
	public List<Metodologia> filtrarPorNombre(String nombre) {
		List<Metodologia> _metodologias;
		
		_metodologias = metodologias.stream().filter(
				metodologia -> metodologia.getNombre().contains(nombre))
				.collect(Collectors.toList());
		
		/*if(_metodologias.isEmpty())
			throw new NoSeEncuentraException("La metodologia con nombre: \""
						+ nombre
						+ "\" no se encuentra en el repositprio");*/

		return _metodologias;
	}

	// Devuelven una lista ordenada de determinada manera, sin alterar las
	// propias del repositorio
	public List<Metodologia> getOrdenadasPorNombre() {
		List<Metodologia> _metodologias = metodologias.stream()
				.sorted(Comparator.comparing(Metodologia::getNombre))
				.collect(Collectors.toList());
		return _metodologias;
	}
	
	//Utilidades
	public int size() {
		return metodologias.size();
	}
	

	public void limpiarRepositorio() {
		metodologias = new ArrayList<Metodologia>();
	}

	private void archivarRepositorio() {
		appData.guardarMetodologias(metodologias);
	}
	
	//Setters y getters
	public List<Metodologia> getMetodologias(){
		return metodologias;
	}
	
	public void setAppData(AppDataI _appData){
		appData = _appData;
	}
	
	private void comprobarNombre(Metodologia metodologia) {
		if(metodologias.contains(metodologia))
			throw new EseYaExisteException("Ya existe una metodologia con el nombre: "
					+ metodologia.getNombre());
	}
}