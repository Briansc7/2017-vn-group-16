package model.metodologia;

import java.util.ArrayList;
import java.util.List;

import model.Empresa;

public class Metodologia {
	String nombre;
	List<CondicionTaxativa> condicionesTaxativas;
	List<CondicionNoTaxativa> condicionesNoTaxativas;
	
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
		
		return aplicarCondicionesNoTaxativas(aplicarCondicionesTaxativas(_empresas));
	}
	
	//TODO Retorna una lista de empresas que cumplan las condiciones
	private List<Empresa> aplicarCondicionesTaxativas(List<Empresa> empresas) {
		return null;
	}

	//TODO Retorna una lista de empresas ordenadas según las condiciones y su peso
	private List<Empresa> aplicarCondicionesNoTaxativas(List<Empresa> empresas) {
		return null;
	}
	
}