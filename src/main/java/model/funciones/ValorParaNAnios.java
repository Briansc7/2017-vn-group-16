package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public class ValorParaNAnios extends Funcion{
	public ValorParaNAnios(Indicador indicador){
		super(indicador);
	}
	
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos){
		return calcularValoresDelPeriodo(empresa, periodo, baseDeDatos);
	}
}
