package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public class Promedio extends Funcion{
	private Indicador indicador;
	
	public Promedio(Indicador indicador){
		super(indicador);
	}
	
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos) {
		BigDecimal suma = sumatoria(empresa, periodo, baseDeDatos);
		BigDecimal[] resultado = new BigDecimal[1];
		resultado[0] = suma.divide(BigDecimal.valueOf(periodo));
		return resultado;
	}
}
