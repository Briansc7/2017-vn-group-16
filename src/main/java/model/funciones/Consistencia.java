package model.funciones;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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
	
	protected Consistencia(){}
	
	public List<BigDecimal> calcularValor(Empresa empresa, Integer periodo) {
		List<BigDecimal> valoresDelPeriodo = this.calcularValoresDelPeriodo(empresa, periodo);
		this.quitarCeros(valoresDelPeriodo);
		BigDecimal resultado;

		if (this.esCreciente(valoresDelPeriodo)) {
	    	resultado = BigDecimal.ONE;
		} else if (this.esDecreciente(valoresDelPeriodo)) {
			resultado = new BigDecimal(-1);
		} else {
			resultado = BigDecimal.ZERO;
		}

	    return Arrays.asList(resultado);
	}

}
