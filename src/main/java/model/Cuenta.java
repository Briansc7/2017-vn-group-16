package model;

import java.time.LocalDate;
import java.time.Year;

import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta extends Factor{
	
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
	
	public Integer getValor(Year unPeriodo, Empresa unaEmpresa) {
		return valor;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
}
