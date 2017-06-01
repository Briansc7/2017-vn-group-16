package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
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
	
	public void borrarIndicadores(){
		this.indicadores.clear();
	}

	
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
	
	public void verificarIndicador(String indicador) throws IOException{
		String[] partes = indicador.split("=");
		try {
			List<String> componentes = ParserTP.parsear(partes[1]);
			if(componentes.contains(partes[0].trim())) {
				throw new RuntimeException("No se puede usar un indicador en su propia definicion");
			}
			this.agregarIndicadorAlArchivo(indicador);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (TokenMgrError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void verificarSintaxisIndicador(String indicador) throws ParseException, TokenMgrError{
		String[] partes = indicador.split("=");
		ParserTP.parsear(partes[1]);
		Planilla.instance.agregarIndicador(new Indicador(partes[0], partes[1]));
	}
	
	private void agregarIndicadorAlArchivo(String indicador) throws IOException{
		File file = new File("./Archivos de prueba/indicadores.txt");
		Writer output = new BufferedWriter(new FileWriter(file, true));
		output.append("\r\n" + indicador);//.append(indicador);
		output.close();
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
