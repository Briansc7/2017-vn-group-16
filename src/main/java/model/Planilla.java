package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import parser.ParseException;
import parser.ParserTP;
import parser.TokenMgrError;

public class Planilla {

	public static Planilla instance = new Planilla();
	private Empresa empresaElegida = null;
	private Integer periodoElegido = null;

	
	private List<Indicador> indicadores = new ArrayList<Indicador>();
	
	/*public Planilla(List<Indicador> indicadores){
		this.indicadores.addAll(indicadores);
	}*/
	public Boolean existeIndicador(String nombre) {
		return this.primero(nombre).isPresent();
	}

	public Optional<Indicador> primero(String nombre) {
		return this.indicadores.stream().filter(indicador -> indicador.getNombre().equalsIgnoreCase(nombre)).findFirst();
	}
	
	public Indicador buscarIndicador(String nombre) {
		if(!this.existeIndicador(nombre)){
			throw new RuntimeException("No existe el indicador");
		}
		return this.primero(nombre).get();
	}
	
	public List<Indicador> indicadoresDelPeriodo() {
		//return Arrays.asList();
		return this.indicadores.stream().filter(indicador -> indicador.existePara(empresaElegida, periodoElegido)).collect(Collectors.toList());
	}
	
	public void verificarIndicador(String indicador) throws ParseException, TokenMgrError{
		String[] partes = indicador.split("=");
		ParserTP.parsear(partes[1]);
		Planilla.instance.agregarIndicador(new Indicador(partes[0], partes[1]));
	}
	
	public void agregarIndicador(Indicador indicador){
		this.indicadores.add(indicador);
	}
	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public Empresa getEmpresaElegida() {
		return empresaElegida;
	}

	public void setEmpresaElegida(Empresa empresaElegida) {
		this.empresaElegida = empresaElegida;
	}

	public Integer getPeriodoElegido() {
		return periodoElegido;
	}

	public void setPeriodoElegido(Integer periodoElegido) {
		this.periodoElegido = periodoElegido;
	}
	
}
