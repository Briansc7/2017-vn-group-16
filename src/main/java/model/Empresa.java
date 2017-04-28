package model;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;

public class Empresa {
	
	String nombre;
	List<Cuenta> cuentas;
	
	@Override
	public String toString(){
		return this.nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	public List<Integer> getPeriodos(){
		return this.cuentas.stream().map((Cuenta cuenta)-> cuenta.getFecha().getYear()).collect(Collectors.toList());
	}

}
