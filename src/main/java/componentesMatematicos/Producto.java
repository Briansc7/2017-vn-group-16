package componentesMatematicos;

import java.time.Year;

import model.Empresa;

public class Producto extends Operador {
	
	public Producto(Expresion opIzq, Expresion opDer){
		
		super(opIzq, opDer);
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa) * this.operandoDer.getValor(unPeriodo, unaEmpresa);
	}
}//