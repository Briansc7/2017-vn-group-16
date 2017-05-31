package model;

import org.uqbar.commons.utils.Observable;

import calculadora.Calculadora;
import calculadora.ParseException;
import calculadora.TokenMgrError;

@Observable
public class Indicador {

	private String nombre;
	private String expresion;
	
	public Indicador(String nombre, String expresion){
		this.nombre = nombre;
		this.expresion = expresion;
	}
	
	public int getValor() throws NumberFormatException, ParseException, TokenMgrError {
		return Calculadora.calcular(this.expresion, Planilla.instance.getEmpresaElegida(), Planilla.instance.getPeriodoElegido());
	}
	
	public String getNombre() {
		return nombre;
	}
}
