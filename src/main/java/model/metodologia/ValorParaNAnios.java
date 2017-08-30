package model.metodologia;

import java.math.BigDecimal;
import java.util.Calendar;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.ValorAComparar;

public class ValorParaNAnios implements Funcion{
	private Indicador indicador;
	
	public ValorParaNAnios(Indicador indicador){
		this.indicador = indicador;
	}
	
	public ValorAComparar calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos){
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);
		BigDecimal[] valores = new BigDecimal[periodo];
		
		for(int i=0; i<periodo; i++){
			valores[i] = indicador.getValor(anioActual-i, empresa, baseDeDatos);//TODO: si no se puede calcular, guardo 0 para ese anio?
		}
		return new ValorAComparar(valores);
	}
}
