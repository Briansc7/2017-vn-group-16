package model;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import calculadora.Calculadora;
import calculadora.ParseException;
import calculadora.TokenMgrError;
import parser.ParserTP;

@Observable
public class Indicador {

	private String nombre;
	private String expresion;
	private List<String> contenido;
	
	public Indicador(String nombre, String expresion) throws parser.ParseException, parser.TokenMgrError{
		this.nombre = nombre;
		this.expresion = expresion;
		this.contenido = ParserTP.parsear(this.expresion);
	}
	
	public int getValor() throws NumberFormatException, ParseException, TokenMgrError {
		return Calculadora.calcular(this.expresion, Planilla.instance.getEmpresaElegida(), Planilla.instance.getPeriodoElegido());
	}
	
	public boolean existePara(Empresa empresa, Integer periodo) {
		return this.contenido.stream().allMatch(nombre -> this.existeComponente(nombre, empresa, periodo));
		
	}
	
	private boolean existeComponente(String nombre, Empresa empresa, Integer periodo){
		if (nombre.substring(0,2).equalsIgnoreCase("c.")){
			return empresa.existeCuentaDel(nombre.substring(2), periodo);
		} else if (Planilla.instance.existeIndicador(nombre)){
			return Planilla.instance.buscarIndicador(nombre).existePara(empresa, periodo);
		}
		return false;
	}
	
	public String getNombre() {
		return nombre;
	}
}
