package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Empresa {
	
	private String nombre;
	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	//private List<Cuenta> cuentas2 = new ArrayList<Cuenta>();
	
	
	public Empresa(String nombre, List<Cuenta> cuentas){
		this.nombre = nombre;
		this.cuentas = cuentas;
	}
	
	public List<Cuenta> cuentasDelPeriodo(Integer periodo){
		
		return this.cuentas.stream().filter((Cuenta cuenta) -> cuenta.getFecha().getYear() == periodo).collect(Collectors.toList());
	}
	
	@Override
	public String toString(){
		return this.nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	
	public List<Integer> getPeriodos(){
		List<Integer> periodos = new ArrayList<Integer>(
				this.cuentas.stream()
				.map((Cuenta cuenta)-> cuenta.getFecha().getYear())
				.collect(Collectors.toSet()));
		
		Collections.sort(periodos);
		return periodos;
	}

	public void agregarCuentas(List<Cuenta> cuenta) {
		cuentas.addAll(cuenta);		
	}
	
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
		//cuentas = cuentas2;
		//cuentas2.add(cuenta);
		//agregarCuentas(cuentas2);
		//cuentas2.add(new Cuenta("Ebitda", Integer.parseInt("123456"), "2017-10-05"));
	}

}
