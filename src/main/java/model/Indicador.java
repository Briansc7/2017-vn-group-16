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
		return 500;//Calculadora.calcular(this.expresion, Planilla.instance.getEmpresaElegida(), Planilla.instance.getPeriodoElegido());
	}
	
	public boolean existePara(Empresa empresa, Integer periodo) {
		//List<String> contenido =
		return this.contenido.stream().allMatch((nombre -> (empresa.existeCuentaDel(nombre, periodo) 
				/*|| Planilla.instance.buscarIndicador(nombre).existePara(empresa, periodo)*/)));//.findFirst().isPresent();
		
		 
	}
	
	public String getNombre() {
		return nombre;
	}
}
