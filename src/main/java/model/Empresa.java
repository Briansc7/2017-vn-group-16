package model;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import exceptions.NoExisteAtributoException;

@Entity
@Table(name = "empresas")
public class Empresa {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "empresa_id")
	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	@SuppressWarnings("unused")
	private Empresa(){}
	
	public Empresa(String nombre, List<Cuenta> cuentas){
		this.nombre = nombre;
		this.cuentas.addAll(cuentas);
	}

	public List<Cuenta> cuentasDelPeriodo(int periodo){
		return this.cuentas.stream().filter((Cuenta cuenta) -> cuenta.getYear() == periodo).collect(Collectors.toList());
	}
	
	public Boolean existeCuentaDel(String nombre, Integer periodo) {
		return this.primero(nombre, periodo).isPresent();
	}

	public Optional<Cuenta> primero(String nombre, Integer periodo) {
		return this.cuentasDelPeriodo(periodo).stream().filter(cuenta -> cuenta.getNombre().equalsIgnoreCase(nombre)).findFirst();
	}

	public Cuenta buscarCuenta(String nombre, Integer periodo){
		if(!this.existeCuentaDel(nombre, periodo)){
			throw new NoExisteAtributoException("No existe la cuenta: " + nombre);
		}
		return this.primero(nombre, periodo).get();
	}
	
	public BigDecimal longevidad() {
		return new BigDecimal(Year.now().getValue() - this.getPeriodos().stream().min((a, b) -> a.compareTo(b)).get());
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
	
	public void agregarCuentas(List<Cuenta> cuentas){
		//todo actualizar cuentas
		this.cuentas.addAll(cuentas);
	}

	public List<Integer> getPeriodos(){
		List<Integer> periodos = new ArrayList<Integer>(
				this.cuentas.stream()
				.map((Cuenta cuenta)-> cuenta.getYear())
				.collect(Collectors.toSet()));
		
		Collections.sort(periodos);
		return periodos;
	}

	public long getId(){
		return id;
	}
}
