package model.metodologia.condiciones;

public class LessThan implements BooleanCondition{
	public boolean comparar(Integer valorUno, Integer valorDos){
		return valorUno < valorDos;
	}
}
