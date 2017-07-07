package model.metodologia.condiciones;

import java.math.BigDecimal;

public interface BooleanCondition {
	//Se compara siempre el valor 1, por ejemplo en 
	//LessThan si valorUno es menor que el valorDos retorna true
	public boolean comparar(BigDecimal valorUno, BigDecimal valorDos);
}