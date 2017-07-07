package componentesMatematicos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import model.BaseDeDatos;
import model.Empresa;

public class Resta extends Operador {
	
	public Resta(Expresion opIzq, Expresion opDer){
		
		super(opIzq, opDer);
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos)
				.subtract(this.operandoDer.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos));
				//.round(new MathContext(2, RoundingMode.CEILING));
	}
}//