package model.metodologia.condiciones;

public class GreaterAndEqualThan implements BooleanCondition{
	public boolean comparar(Integer valorUno, Integer valorDos){
		return valorUno >= valorDos;
	}
}
