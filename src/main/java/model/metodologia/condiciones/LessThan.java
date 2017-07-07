package model.metodologia.condiciones;

import java.math.BigDecimal;


public class LessThan implements BooleanCondition{
	public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
		return valorUno.compareTo(valorDos) < 0;
	}
	
	public LessThan() {
		super();
	}
	
	@Override
	public String toString(){
		return "Menor";
	}
}
