package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;
//TODO: se usa?
public class ValorParaXAnio implements Funcion{
	private Indicador indicador;
	
	public ValorParaXAnio(Indicador indicador){
		this.indicador = indicador;
	}
	
	public BigDecimal[] calcularValor(Empresa empresa, Integer anio, BaseDeDatos baseDeDatos){
		BigDecimal[] resultado = new BigDecimal[1];
		resultado[0] = indicador.getValor(anio, empresa, baseDeDatos);
		return resultado;
	}
}
