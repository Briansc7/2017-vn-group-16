package componentesMatematicos;

import java.time.Year;

import model.Empresa;

public interface Expresion {
	
	public Integer getValor(Year unPeriodo, Empresa unaEmpresa);
}