package model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.uqbar.commons.utils.Observable;

@Observable
public class Cuenta extends Atributo{
	
	private BigDecimal valor;
	private LocalDate fecha;

	public Cuenta(String nombre, BigDecimal valor, LocalDate fecha){
		super(nombre);
		this.valor = valor;
		this.fecha = fecha;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public Integer getYear() {
		return this.fecha.getYear();
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
