package componentesMatematicos;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;

public interface Expresion {
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos);
} 