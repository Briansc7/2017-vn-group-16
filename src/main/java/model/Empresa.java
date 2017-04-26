package model;

import java.util.List;

public class Empresa {
	
	String nombre;
	List<Cuenta> cuentas;
	
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
	

}
