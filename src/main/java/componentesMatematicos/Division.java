package componentesMatematicos;

import exceptions.NoSePuedeDividirPorCeroException;
import model.BaseDeDatos;
import model.Empresa;

public class Division extends Operador {
	
	public Division(Expresion opIzq, Expresion opDer){
		
		super(opIzq, opDer);
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		
		if(this.operandoDer.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos) != 0){
			return this.operandoIzq.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos) / this.operandoDer.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos);
		}
		else throw new NoSePuedeDividirPorCeroException("No se puede dividir por 0");
	}
}//