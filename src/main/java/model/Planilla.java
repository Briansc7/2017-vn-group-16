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

import componentesMatematicos.Expresion;
import parser.ParseException;
import parser.Parser;
import parser.TokenMgrError;

public class Planilla {

	private List<Indicador> indicadores = new ArrayList<Indicador>();
	private String pathIndicadores = "./Archivos del sistema/indicadores.txt";
	
	public void borrarIndicadores(){
		this.indicadores.clear();
	}

	
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
	
	public void verificarIndicador(String indicador) throws IOException, ParseException, TokenMgrError{
		String[] partes = indicador.split("=");
		if(partes[1].toLowerCase().contains(partes[0].trim().toLowerCase())){
			 throw new RuntimeException("No se puede usar un indicador en su propia definicion");
		}
		this.agregarIndicadorAlArchivo(indicador);
	}
	
	public void verificarIndicadorParaAgregar(String indicador) throws ParseException, TokenMgrError{
		String[] partes = indicador.split("=");
		
		if(verificarSintaxisIndicador(partes[0].trim(), partes[1]) && verificarAusenteEnLista(partes[0].trim(), partes[1])){
			String auxiliar= "No encontrado pero valido ";
			System.out.println(auxiliar);
			System.out.println(partes[0].trim());
			System.out.println(partes[1]);
			
			this.agregarIndicador(new Indicador(partes[0].trim(), partes[1]));
		} 
		//throw new RuntimeException("No se puede usar un indicador en su propia definicion");
	}
	
	// Verifica sintaxis = que el indicador no se llame a si mismo
	public boolean verificarSintaxisIndicador(String nombreIndicador, String contenidoFormula) throws ParseException, TokenMgrError{
		return !contenidoFormula.toLowerCase().contains(nombreIndicador.toLowerCase());
	}
	
	// Verifica que el indicador no haya sido cargado en la lista con la misma formula (Evito asi parsear N veces)
	public boolean verificarAusenteEnLista(String nombreIndicador, String contenidoFormula) throws ParseException, TokenMgrError{
		return this.indicadores.stream().filter(indicador -> indicador.esIdentico(nombreIndicador,contenidoFormula)).collect(Collectors.toList()).isEmpty();
	}
	
	public void agregarIndicadorAlArchivo(String indicador) throws IOException{
		File file = new File(this.pathIndicadores);
		Writer output = new BufferedWriter(new FileWriter(file, true));
		output.append(indicador + "\r\n");
		output.close();
	}
	
	public void agregarIndicador(Indicador indicador){
		this.indicadores.add(indicador);
	}
	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setPathIndicadores(String pathIndicadores) {
		this.pathIndicadores = pathIndicadores;
	}
}
