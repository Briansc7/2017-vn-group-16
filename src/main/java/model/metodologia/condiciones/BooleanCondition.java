package model.metodologia.condiciones;

public interface BooleanCondition {
	//Se compara siempre el valor 1, por ejemplo en 
	//LessThan si valorUno es menor que el valorDos retorna true
	public boolean comparar(Integer valorUno, Integer valorDos);
}