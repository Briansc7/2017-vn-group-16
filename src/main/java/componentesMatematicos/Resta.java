package componentesMatematicos;

import java.time.Year;

import model.Empresa;

public class Resta extends Operador {
	
	public Resta(Expresion opIzq, Expresion opDer){
		
		super(opIzq, opDer);
	}
	
	public Integer getValor(Year unPeriodo, Empresa unaEmpresa) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa) - this.operandoDer.getValor(unPeriodo, unaEmpresa);
	}
}