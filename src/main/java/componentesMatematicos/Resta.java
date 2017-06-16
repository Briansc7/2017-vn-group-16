package componentesMatematicos;

import java.time.Year;

import model.Empresa;
import model.Planilla;

public class Resta extends Operador {
	
	public Resta(Expresion opIzq, Expresion opDer){
		
		super(opIzq, opDer);
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, Planilla unaPlanilla) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa, unaPlanilla) - this.operandoDer.getValor(unPeriodo, unaEmpresa, unaPlanilla);
	}
}//