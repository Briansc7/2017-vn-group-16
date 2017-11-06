package model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import converters.LocalDateConverter;

@Entity
@DiscriminatorValue("cuenta")
public class Cuenta extends Atributo{
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "fecha")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate fecha;

	public Cuenta(String nombre, BigDecimal valor, LocalDate fecha){
		super(nombre);
		this.valor = valor;
		this.fecha = fecha;
	}
	
	@SuppressWarnings("unused")
	private Cuenta(){}
	
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
}
