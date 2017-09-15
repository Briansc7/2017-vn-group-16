package model.funciones;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;
import model.Indicador;

@Entity
@DiscriminatorValue("consistencia")
public class Consistencia extends Funcion{
	public Consistencia(Indicador indicador){
		super(indicador);
	}
	
	public Consistencia(){}
	
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo) {
		BigDecimal[] valoresDelPeriodo = calcularValoresDelPeriodo(empresa, periodo);
		BigDecimal[] resultado = new BigDecimal[1];
		int fin = periodo-1;
	    int contadorAsc = 0;
	    int contadorDesc = 0;

	    for (int i = 0; i < fin; i++) {
	        if(valoresDelPeriodo[i].compareTo(valoresDelPeriodo[i+1]) > 0){
	        	contadorAsc++;
	        } else if(valoresDelPeriodo[i].compareTo(valoresDelPeriodo[i+1]) < 0){
	        	contadorDesc++;
	        }
	    }
	    //TODO: devuelve 1 si es creciente, -1 si es decreciente y 0 si no es ninguna
	    if(contadorDesc == 0){
	        resultado[0] = BigDecimal.ONE;
	    } else if(contadorAsc == 0){
	    	resultado[0] = new BigDecimal(-1);
	    } else {
	    	resultado[0] = BigDecimal.ZERO;
	    }
	    return resultado;
	}
}
