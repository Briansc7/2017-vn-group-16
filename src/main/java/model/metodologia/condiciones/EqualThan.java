package model.metodologia.condiciones;

public class EqualThan implements BooleanCondition{
	public boolean comprar(Integer valorUno, Integer valorDos){
		return valorUno == valorDos;
	}
}
