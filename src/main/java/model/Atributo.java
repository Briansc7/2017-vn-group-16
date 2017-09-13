package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Atributo {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre")
	public String nombre;
	
	public Atributo(){}
	
	public Atributo(String unNombre){
		this.nombre = unNombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
