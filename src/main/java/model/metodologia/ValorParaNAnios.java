package model.metodologia;

import java.math.BigDecimal;
import java.util.Calendar;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public class ValorParaNAnios implements Funcion{
	private Indicador indicador;
	
	public ValorParaNAnios(Indicador indicador){
		this.indicador = indicador;
	}
	
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos){
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);
		BigDecimal[] valores = new BigDecimal[periodo];
		
		for(int i=0; i<periodo; i++){
			try{
				valores[i] = indicador.getValor(anioActual-i, empresa, baseDeDatos);
			} catch (Exception ex) {
				//valorAuxiliar = "*";
				valores[i] = BigDecimal.ZERO;
			} 
		}
		return valores;
	}
}
