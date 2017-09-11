package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

@Observable
@Entity
public class Cuenta extends Atributo{
	
	@Id
	@GeneratedValue
	private Long id;
	
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
