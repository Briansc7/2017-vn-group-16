package model;

import org.joda.time.LocalDate;
import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta {
	
	private String nombre;
	private Integer valor;
	private String fecha;

	
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
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public LocalDate getFecha() {
		return LocalDate.parse(this.fecha);
	}
	
}
