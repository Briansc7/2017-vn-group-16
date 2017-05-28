package model;

import org.uqbar.commons.utils.Observable;

import parser.ParseException;
import parser.ParserTP;
import parser.TokenMgrError;

@Observable
public class Indicador {

	private String nombre;
	private String expresion;
	
	public Indicador(String nombre, String expresion){
		this.nombre = nombre;
		this.expresion = expresion;
	}
	
	public int getValor() throws NumberFormatException, ParseException, TokenMgrError {
		return ParserTP.parsear(this.expresion, Planilla.instance.getEmpresaElegida(), Planilla.instance.getPeriodoElegido());
	}
	
	public String getNombre() {
		return nombre;
	}
}
