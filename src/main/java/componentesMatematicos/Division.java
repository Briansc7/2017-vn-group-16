package componentesMatematicos;

import java.time.Year;

import org.uqbar.commons.model.UserException;

import exceptions.NoSePuedeDividirPorCeroException;
import model.Empresa;
import model.Planilla;

public class Division extends Operador {
	
	public Division(Expresion opIzq, Expresion opDer){
		
		super(opIzq, opDer);
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, Planilla unaPlanilla) {
		
		if(this.operandoDer.getValor(unPeriodo, unaEmpresa, unaPlanilla) != 0){
			return this.operandoIzq.getValor(unPeriodo, unaEmpresa, unaPlanilla) / this.operandoDer.getValor(unPeriodo, unaEmpresa, unaPlanilla);
		}
		else throw new NoSePuedeDividirPorCeroException("No se puede dividir por 0");
	}
}//