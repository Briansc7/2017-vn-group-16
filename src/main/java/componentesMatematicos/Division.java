package componentesMatematicos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.NoSePuedeDividirPorCeroException;
import model.Empresa;

@Entity
@DiscriminatorValue("5")
public class Division extends Operador {

	private Division(){
		super();
	}

	public Division(Expresion opIzq, Expresion opDer){
		super(opIzq, opDer);
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa) {
		if(this.operandoDer.getValor(unPeriodo, unaEmpresa).compareTo(BigDecimal.ZERO) != 0){
			return this.operandoIzq.getValor(unPeriodo, unaEmpresa)
					.divide(this.operandoDer.getValor(unPeriodo, unaEmpresa), 2, RoundingMode.HALF_UP);
					//.round(new MathContext(2, RoundingMode.HALF_UP));
		}
		else throw new NoSePuedeDividirPorCeroException("No se puede dividir por 0");
	}
}//