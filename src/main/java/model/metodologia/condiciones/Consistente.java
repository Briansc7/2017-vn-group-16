package model.metodologia.condiciones;

import java.math.BigDecimal;

public class Consistente implements BooleanCondition{
	public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
		return valorDos.compareTo(valorUno.multiply(BigDecimal.valueOf(0.9))) >= 0 && 
				valorDos.compareTo(valorUno.multiply(BigDecimal.valueOf(1.1))) <= 0;
	}
}
