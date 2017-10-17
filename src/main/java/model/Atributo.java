package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "atributos")
@DiscriminatorColumn(name = "tipo_de_atributo")
public abstract class Atributo {
	
	@Id
	@GeneratedValue
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
