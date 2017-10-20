package model;

import org.uqbar.commons.utils.Observable;

@Observable
public class IndicadorAuxiliar {
	private String nombre;
	private String valorString;
	
	public IndicadorAuxiliar(String unNombre, String unValorString){
		nombre = unNombre;
		valorString = unValorString;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getValorString() {
		return valorString;
	}
}
