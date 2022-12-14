package componentesMatematicos;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;

@Entity
@DiscriminatorValue("8")
public class Resta extends Operador {
	
	private Resta(){super();}
	
	public Resta(Expresion opIzq, Expresion opDer){
		super(opIzq, opDer);
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa)
				.subtract(this.operandoDer.getValor(unPeriodo, unaEmpresa));
				//.round(new MathContext(2, RoundingMode.CEILING));
	}
}//