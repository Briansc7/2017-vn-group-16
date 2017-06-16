package model;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Empresa {
	
	private String nombre;
	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	
	public Empresa(String nombre, List<Cuenta> cuentas){
		this.nombre = nombre;
		this.cuentas.addAll(cuentas);
	}

	
	public List<Cuenta> cuentasDelPeriodo(int periodo){
		
		return this.cuentas;//.stream().filter((Cuenta cuenta) -> cuenta.getFecha().getYear() == periodo).collect(Collectors.toList());
	}
	
	
	public Boolean existeCuentaDel(String nombre, Integer periodo) {
		return this.primero(nombre, periodo).isPresent();
	}

	public Optional<Cuenta> primero(String nombre, Integer periodo) {
		return this.cuentasDelPeriodo(periodo).stream().filter(cuenta -> cuenta.getNombre().equalsIgnoreCase(nombre)).findFirst();
	}

	public Cuenta buscarCuenta(String nombre, Integer periodo){
		if(!this.existeCuentaDel(nombre, periodo)){
			throw new RuntimeException("No existe la cuenta " + nombre + " en el periodo " + periodo);
		}
		return this.primero(nombre, periodo).get();
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
				.map((Cuenta cuenta)-> cuenta.getFecha().getYear())//solo get year, que lo haga la cuenta
				//usar localdate de java, hay clase llamada year
				.collect(Collectors.toSet()));
		
		Collections.sort(periodos);
		return periodos;
	}

}
