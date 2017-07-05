package componentesMatematicos;

import model.BaseDeDatos;
import model.Empresa;

public class Producto extends Operador {
	
	public Producto(Expresion opIzq, Expresion opDer){
		
		super(opIzq, opDer);
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos) * this.operandoDer.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos);
	}
}//