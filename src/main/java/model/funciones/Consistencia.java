package model.funciones;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.google.common.collect.Ordering;
import model.Empresa;
import model.Indicador;

@Entity
@DiscriminatorValue("consistencia")
public class Consistencia extends Funcion{
	public Consistencia(Indicador indicador){
		super(indicador);
	}
	
	public Consistencia(){}
	
	public List<BigDecimal> calcularValor(Empresa empresa, Integer periodo) {
		List<BigDecimal> valoresDelPeriodo = calcularValoresDelPeriodo(empresa, periodo);
		BigDecimal resultado;
//		int fin = periodo-1;
//	    int contadorAsc = 0;
//	    int contadorDesc = 0;
//
//	    for(int i = 0; i < fin; i++) {
//	        if(valoresDelPeriodo.get(i).compareTo(valoresDelPeriodo.get(i+1)) > 0){
//	        	contadorAsc++;
//	        } else if(valoresDelPeriodo.get(i).compareTo(valoresDelPeriodo.get(i+1)) < 0){
//	        	contadorDesc++;
//	        }
//	    }

		//devuelve 1 si es creciente, -1 si es decreciente y 0 si no es ninguna
		if (this.esCreciente(valoresDelPeriodo)) {
	    	resultado = BigDecimal.ONE;
		} else if (this.esDecreciente(valoresDelPeriodo)) {
			resultado = new BigDecimal(-1);
		} else {
			resultado = BigDecimal.ZERO;
		}

//	    /** */
//	    if(contadorDesc == 0){
//	        resultado = BigDecimal.ONE;
//	    } else if(contadorAsc == 0){
//	    	resultado = new BigDecimal(-1);
//	    } else {
//	    	resultado = BigDecimal.ZERO;
//	    }
	    return Arrays.asList(resultado);
	}

}
