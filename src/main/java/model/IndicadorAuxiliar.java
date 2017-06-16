package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class IndicadorAuxiliar {
	private String nombre;
	private int valor;
	
	public IndicadorAuxiliar(String unNombre, int unValor){
		this.nombre = unNombre;
		this.valor = unValor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getValor() {
		return valor;
	}
}
