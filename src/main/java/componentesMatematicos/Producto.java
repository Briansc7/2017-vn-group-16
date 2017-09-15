package componentesMatematicos;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;

@Entity
@DiscriminatorValue("7")
public class Producto extends Operador {
	
	private Producto(){super();}
	
	public Producto(Expresion opIzq, Expresion opDer){
		super(opIzq, opDer);
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa) {
		
		return this.operandoIzq.getValor(unPeriodo, unaEmpresa)
				.multiply(this.operandoDer.getValor(unPeriodo, unaEmpresa));
				//.round(new MathContext(2, RoundingMode.CEILING));
	}
}//