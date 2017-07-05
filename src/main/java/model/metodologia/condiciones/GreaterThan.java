package model.metodologia.condiciones;

public class GreaterThan implements BooleanCondition{
	public boolean comprar(Integer valorUno, Integer valorDos){
		return valorUno > valorDos;
	}
}
