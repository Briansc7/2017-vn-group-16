package model;

import java.time.LocalDate;

import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta implements Factor{
	
	private String nombre;
	private Integer valor;
	private LocalDate fecha;

	public Cuenta(String nombre, Integer valor, LocalDate fecha){
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
		return this.fecha;
	}
	
}
