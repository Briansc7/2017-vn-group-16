package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public class Consistencia implements Funcion{
	private Indicador indicador;
	
	public Consistencia(Indicador indicador){
		this.indicador = indicador;
	}

	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos) {
		return null;
	}
	
}
