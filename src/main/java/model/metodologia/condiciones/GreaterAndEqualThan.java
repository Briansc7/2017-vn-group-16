package model.metodologia.condiciones;

import java.math.BigDecimal;

public class GreaterAndEqualThan implements BooleanCondition{
	public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
		return valorUno.compareTo(valorDos) >= 0;
	}
	
	public GreaterAndEqualThan() {
		super();
	}
	
	@Override
	public String toString(){
		return "Mayor o igual";
	}
}
