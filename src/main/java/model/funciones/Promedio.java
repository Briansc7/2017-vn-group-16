package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public class Promedio implements Funcion{
	private Indicador indicador;
	
	public Promedio(Indicador indicador){
		this.indicador = indicador;
	}
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos) {
		BigDecimal contador = BigDecimal.ZERO;
		BigDecimal[] resultado = new BigDecimal[1];
		for(int i=0; i<periodo; i++){
			contador = contador.add(indicador.getValor(periodo-i, empresa, baseDeDatos));
		}
		resultado[0] = contador.divide(BigDecimal.valueOf(periodo));
		return resultado;
	}
}
