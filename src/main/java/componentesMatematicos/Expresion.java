package componentesMatematicos;

import model.BaseDeDatos;
import model.Empresa;

public interface Expresion {
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos);
} 