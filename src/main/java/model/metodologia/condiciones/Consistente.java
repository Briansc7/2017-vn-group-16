package model.metodologia.condiciones;

public class Consistente implements BooleanCondition{
	public boolean comparar(Integer valorUno, Integer valorDos){
		float valor1 = valorUno;
		float valor2 = valorDos;
		return (valor1/valor2) > valor1*0.9 && (valor1/valor2) < valor1*1.1;
	}
}
