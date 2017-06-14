package model;

import java.time.LocalDate;
import java.time.Year;

import org.uqbar.commons.utils.Observable;

import model.Atributo;

@Observable
public class Cuenta extends Atributo{
	
	private String nombre;
	private Integer valor;
	private LocalDate fecha;

	public Cuenta(String nombre, Integer valor, LocalDate fecha){
		super(nombre);
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
