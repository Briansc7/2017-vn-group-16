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
	List<CondicionTaxativa> condicionesTaxativas;
	List<CondicionNoTaxativa> condicionesNoTaxativas;
	List<Condicion> condi = Arrays.asList(new Condicion(2170, "", new LessThan()), new CondicionTaxativa(2170, "", new LessThan(), new BigDecimal(2)), new CondicionNoTaxativa(2017,"", new LessThan(), 2));
	
	
	
	@Override
	public boolean equals(Object o){
		if(!o.getClass().equals(Metodologia.class))
			return false;
		
		Metodologia other = (Metodologia) o;
        return other.getNombre().equals(getNombre());
	}
	
	public Metodologia(String nombre, List<CondicionTaxativa> condicionesTaxativas,
			List<CondicionNoTaxativa> condicionesNoTaxativas) {
		
		this.nombre = nombre;
		this.condicionesTaxativas = condicionesTaxativas;
		this.condicionesNoTaxativas = condicionesNoTaxativas;
	}

	//Retorna la empresas que cumplen con todo y ordenadas
	public List<Empresa> aplicarCondiciones(List<Empresa> empresas, BaseDeDatos baseDeDatos) {
		//No alterar la lista original
		List<Empresa> _empresas = new ArrayList<Empresa>(empresas);
		
		return aplicarCondicionesNoTaxativas(aplicarCondicionesTaxativas(_empresas, baseDeDatos), baseDeDatos);
	}
	
	//TODO Retorna una lista de empresas que cumplan las condiciones
	private List<Empresa> aplicarCondicionesTaxativas(List<Empresa> empresas, BaseDeDatos baseDeDatos) {
		return empresas.stream().filter(empresa -> condicionesTaxativas.stream().allMatch(condicion -> condicion.aplicarCondicion(empresa, baseDeDatos))).collect(Collectors.toList());
	}

	//TODO Retorna una lista de empresas ordenadas seg�n las condiciones y su peso
	private List<Empresa> aplicarCondicionesNoTaxativas(List<Empresa> empresas, BaseDeDatos baseDeDatos) {
		empresas.sort((empresa1, empresa2)-> this.mejorPuntaje(empresa1, empresa2, baseDeDatos));
		return empresas;
	}
	
	private int mejorPuntaje(Empresa empresaUno, Empresa empresaDos, BaseDeDatos baseDeDatos){
		int puntosE1 = 0, puntosE2 = 0;
		for(CondicionNoTaxativa condicion : condicionesNoTaxativas){
			if(condicion.compararEmpresas(empresaUno, empresaDos, baseDeDatos))
				puntosE1 += condicion.getPesoEstimado();
			else
				puntosE2 += condicion.getPesoEstimado();
		}
		return Integer.compare(puntosE2, puntosE1);
	}
	
	@Override
	public String toString(){
		return this.nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public List<CondicionNoTaxativa> getCriterios() {
		
		return condicionesNoTaxativas;
	}
}