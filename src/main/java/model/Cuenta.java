package model;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	
	private String nombre;
	private Integer valor;
	private String fecha;

	public Cuenta(String nombre, Integer valor, String fecha){
		this.nombre = nombre;
		this.valor = valor;
		this.fecha = fecha;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public LocalDate getFecha() {
		return LocalDate.parse(this.fecha);
	}
	
}
