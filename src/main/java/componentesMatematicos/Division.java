package componentesMatematicos;

import java.time.Year;

import org.uqbar.commons.model.UserException;

import exceptions.NoSePuedeDividirPorCeroException;
import model.Empresa;

public class Division extends Operador {
	
	public Division(Expresion opIzq, Expresion opDer){
		
		super(opIzq, opDer);
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa) {
		
		if(this.operandoDer.getValor(unPeriodo, unaEmpresa) != 0){
			return this.operandoIzq.getValor(unPeriodo, unaEmpresa) / this.operandoDer.getValor(unPeriodo, unaEmpresa);
		}
		else throw new NoSePuedeDividirPorCeroException("No se puede dividir por 0");
	}
}