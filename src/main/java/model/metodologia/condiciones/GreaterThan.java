package model.metodologia.condiciones;

public class GreaterThan implements BooleanCondition{
	public boolean comparar(Integer valorUno, Integer valorDos){
		return valorUno > valorDos;
	}
}
