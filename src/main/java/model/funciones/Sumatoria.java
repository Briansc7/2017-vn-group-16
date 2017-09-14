package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public class Sumatoria extends Funcion{
	public Sumatoria(Indicador indicador){
		super(indicador);
	}

	@Override
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos) {
		BigDecimal[] resultado = {sumatoria(empresa, periodo, baseDeDatos)};
		return resultado;
	}
}
