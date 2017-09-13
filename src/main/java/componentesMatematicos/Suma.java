package componentesMatematicos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.BaseDeDatos;
import model.Empresa;

@Entity
@DiscriminatorValue("9")
public class Suma extends Operador {
	
	private Suma(){super();}
	
	public Suma(Expresion opIzq, Expresion opDer){
		super(opIzq, opDer);
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos)
				.add(this.operandoDer.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos));
				//.round(new MathContext(2, RoundingMode.HALF_UP));
	}
}//