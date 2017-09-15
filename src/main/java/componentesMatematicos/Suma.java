package componentesMatematicos;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;

@Entity
@DiscriminatorValue("9")
public class Suma extends Operador {
	
	private Suma(){super();}
	
	public Suma(Expresion opIzq, Expresion opDer){
		super(opIzq, opDer);
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa)
				.add(this.operandoDer.getValor(unPeriodo, unaEmpresa));
				//.round(new MathContext(2, RoundingMode.HALF_UP));
	}
}//