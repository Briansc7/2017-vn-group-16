package componentesMatematicos;

public abstract class Operador implements Expresion {
	
	public Expresion operandoIzq;
	public Expresion operandoDer;
	
	public Operador(Expresion opIzq, Expresion opDer){
		
		this.operandoIzq = opIzq;
		this.operandoDer = opDer;
	}
}