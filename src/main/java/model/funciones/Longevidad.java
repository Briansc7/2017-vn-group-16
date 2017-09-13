package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;

public class Longevidad implements Funcion{
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos) {
		BigDecimal[] resultado = new BigDecimal[1];
		resultado[0] = empresa.longevidad();
		return resultado;
	}
}
