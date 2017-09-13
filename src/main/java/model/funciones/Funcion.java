package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public abstract class Funcion {
	protected Indicador indicador;
	
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
}
