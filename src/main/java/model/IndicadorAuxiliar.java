package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class IndicadorAuxiliar {
	private String nombre;
	private int valor;
	private String valorString;
	
//	public IndicadorAuxiliar(String unNombre, int unValor){
	public IndicadorAuxiliar(String unNombre, String unValorString){	
		this.nombre = unNombre;
//		this.valor = unValor;
		this.valorString = unValorString;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getValor() {
		return valor;
	}
	
	public String getValorString() {
		return valorString;
	}
}
