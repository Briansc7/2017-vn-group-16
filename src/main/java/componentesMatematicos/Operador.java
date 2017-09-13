package componentesMatematicos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("4")
public abstract class Operador extends Expresion {
	
	@OneToOne
	public Expresion operandoIzq;
	@OneToOne
	public Expresion operandoDer;
	
	public Operador(){}
	
	public Operador(Expresion opIzq, Expresion opDer){
		
		this.operandoIzq = opIzq;
		this.operandoDer = opDer;
	}
}