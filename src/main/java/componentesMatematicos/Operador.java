package componentesMatematicos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("4")
public abstract class Operador extends Expresion {
	
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	public Expresion operandoIzq;
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	public Expresion operandoDer;
	
	public Operador(){}
	
	public Operador(Expresion opIzq, Expresion opDer){
		
		this.operandoIzq = opIzq;
		this.operandoDer = opDer;
	}
}