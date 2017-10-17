package componentesMatematicos;

import javax.persistence.*;

@Entity
@DiscriminatorValue("4")
public abstract class Operador extends Expresion {
	
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	//@Column(name = "operando_izq_id")
	public Expresion operandoIzq;

	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	//@Column(name = "operando_der_id")
	public Expresion operandoDer;
	
	public Operador(){}
	
	public Operador(Expresion opIzq, Expresion opDer){
		
		this.operandoIzq = opIzq;
		this.operandoDer = opDer;
	}
}