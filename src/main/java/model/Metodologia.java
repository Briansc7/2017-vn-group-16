package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.condiciones.Condicion;

@Entity
@Table(name = "metodologias")
public class Metodologia {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre")
	String nombre;
	
	@ManyToOne( cascade = CascadeType.PERSIST)
	private Long usuario_id;
	
	@Column
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "metodologia_id")
	List<Condicion> condiciones;
	//List<Condicion> condi = Arrays.asList(new Condicion(2170, "", new LessThan()), new CondicionTaxativa(2170, "", new LessThan(), new BigDecimal(2)), new CondicionNoTaxativa(2017,"", new LessThan(), 2));
	
	private Metodologia(){};
	
	public Metodologia(String nombre, List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones= condiciones;
	}

	//Retorna la empresas que cumplen con todo y ordenadas
	public List<Empresa> aplicarCondiciones(List<Empresa> empresas) {
		//No alterar la lista original
		List<Empresa> _empresas = new ArrayList<Empresa>(empresas);
		
		for(Condicion condicion:condiciones){
			_empresas = condicion.analizar(_empresas);
		}
		return _empresas;
	}
	
	
	@Override
	public String toString(){
		return this.nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
	

	
	/*
	private List<Empresa> aplicarCondicionesTaxativas(List<Empresa> empresas, BaseDeDatos baseDeDatos) {
		return empresas.stream().filter(empresa -> condicionesTaxativas.stream().allMatch(condicion -> condicion.aplicarCondicion(empresa, baseDeDatos))).collect(Collectors.toList());
	}

	private List<Empresa> aplicarCondicionesNoTaxativas(List<Empresa> empresas, BaseDeDatos baseDeDatos) {
		empresas.sort((empresa1, empresa2)-> this.mejorPuntaje(empresa1, empresa2, baseDeDatos));
		return empresas;
	}
	public List<CondicionNoTaxativa> getCriterios() {
		
		return condicionesNoTaxativas;
	}
	 */
}