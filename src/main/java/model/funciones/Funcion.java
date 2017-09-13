package model.funciones;

import java.math.BigDecimal;
import java.util.Calendar;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public abstract class Funcion {
	protected Indicador indicador;
	
	public Funcion(Indicador indicador){
		this.indicador = indicador;
	}
	
	public Funcion(){}
	
	public abstract BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos);
	
	public void setIndicador(Indicador indicador){
		this.indicador = indicador;
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}

	public boolean seLlama(String nombre) {
		return this.getClass().getSimpleName().equalsIgnoreCase(nombre);
	}
	
	protected BigDecimal[] calcularValoresDelPeriodo(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos) {
		BigDecimal[] valoresDelPeriodo = new BigDecimal[periodo];
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);
		
		for(int i=0; i<periodo; i++){
			try{
				valoresDelPeriodo[i] = indicador.getValor(anioActual-i, empresa, baseDeDatos);
			} catch (Exception ex) {
				//valorAuxiliar = "*";
				valoresDelPeriodo[i] = BigDecimal.ZERO;
			} 
		}
		
		return valoresDelPeriodo;
	}
}
