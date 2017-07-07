package model.metodologia.condiciones;

import java.math.BigDecimal;

public class EqualThan implements BooleanCondition{
	public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
		return valorUno.compareTo(valorDos) == 0;
	}
	
	public EqualThan() {
		super();
	}

	@Override
	public String toString(){
		return "Igual";
	}
}
