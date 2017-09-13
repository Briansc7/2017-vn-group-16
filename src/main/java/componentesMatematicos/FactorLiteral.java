package componentesMatematicos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public abstract class FactorLiteral extends Factor {
	
	public FactorLiteral() {}
	
	@Column(name = "nombre")
	public String nombre;
	
	public FactorLiteral(String unNombre){
		this.nombre = unNombre;
	}
}
