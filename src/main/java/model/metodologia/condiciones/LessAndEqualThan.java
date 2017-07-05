package model.metodologia.condiciones;

public class LessAndEqualThan implements BooleanCondition{
	public boolean comprar(Integer valorUno, Integer valorDos){
		return valorUno <= valorDos;
	}
}
