package model;

import java.util.List;

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
	
	public List<LocalDate> getPeriodos(){
		this.cuentas
	}

}
