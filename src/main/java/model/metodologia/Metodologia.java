package model.metodologia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.condiciones.LessThan;

public class Metodologia {
	String nombre;
	List<Condicion> condiciones;
	List<CondicionTaxativa> condicionesTaxativas;
	List<CondicionNoTaxativa> condicionesNoTaxativas;
	//List<Condicion> condi = Arrays.asList(new Condicion(2170, "", new LessThan()), new CondicionTaxativa(2170, "", new LessThan(), new BigDecimal(2)), new CondicionNoTaxativa(2017,"", new LessThan(), 2));
	
	public Metodologia(String nombre, List<CondicionTaxativa> condicionesTaxativas,
			List<CondicionNoTaxativa> condicionesNoTaxativas) {
		
		this.nombre = nombre;
		this.condicionesTaxativas = condicionesTaxativas;
		this.condicionesNoTaxativas = condicionesNoTaxativas;
	}

	//Retorna la empresas que cumplen con todo y ordenadas
	public List<Empresa> aplicarCondiciones(List<Empresa> empresas) {
		//No alterar la lista original
		List<Empresa> _empresas = new ArrayList<Empresa>(empresas);
		
		
		return aplicarCondiciones(_empresas);
	}
	
	/*
	private List<Empresa> aplicarCondicionesTaxativas(List<Empresa> empresas, BaseDeDatos baseDeDatos) {
		return empresas.stream().filter(empresa -> condicionesTaxativas.stream().allMatch(condicion -> condicion.aplicarCondicion(empresa, baseDeDatos))).collect(Collectors.toList());
	}

	private List<Empresa> aplicarCondicionesNoTaxativas(List<Empresa> empresas, BaseDeDatos baseDeDatos) {
		empresas.sort((empresa1, empresa2)-> this.mejorPuntaje(empresa1, empresa2, baseDeDatos));
		return empresas;
	}*/
	
	@Override
	public String toString(){
		return this.nombre;
	}
	
	public String getNombre(){
		return nombre;
	}

	public List<CondicionNoTaxativa> getCriterios() {
		
		return condicionesNoTaxativas;
	}
	
	@Override
	public boolean equals(Object o){
		if(!o.getClass().equals(Metodologia.class))
			return false;
		
		Metodologia other = (Metodologia) o;
		return other.getNombre().equals(getNombre());
	}
	
}