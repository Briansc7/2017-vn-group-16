package model.metodologia.condiciones;

public class GreaterAndEqualThan implements BooleanCondition{
	public boolean comprar(Integer valorUno, Integer valorDos){
		return valorUno >= valorDos;
	}
}
