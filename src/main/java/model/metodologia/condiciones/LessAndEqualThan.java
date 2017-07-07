package model.metodologia.condiciones;

import java.math.BigDecimal;

public class LessAndEqualThan implements BooleanCondition{
	public boolean comparar(BigDecimal valorUno, BigDecimal valorDos){
		return valorUno.compareTo(valorDos) <= 0;
	}
}
