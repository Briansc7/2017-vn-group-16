package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Empresa {
	
	String nombre;
	List<Cuenta> cuentas;
	
	public List<Cuenta> cuentasDelPeriodo(Integer periodo){
		List<Cuenta> list;
		
		list = this.cuentas.stream().filter((Cuenta cuenta) -> cuenta.getFecha().getYear() == periodo).collect(Collectors.toList());
		return list;
	}
	
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
		List<Integer> periodos = this.cuentas.stream().map((Cuenta cuenta)-> cuenta.getFecha().getYear()).collect(Collectors.toList());
		
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.addAll(periodos); 
		periodos.clear();
		periodos.addAll(hs);
		Collections.sort(periodos);
		return periodos;
	}

}
