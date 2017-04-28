package model;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	
	String nombre;
	Integer valor;
	String fecha;

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public LocalDate getFecha() {
		return LocalDate.parse(this.fecha);
	}
	
}
