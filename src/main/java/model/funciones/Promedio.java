package model.funciones;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;
import model.Indicador;

@Entity
@DiscriminatorValue("promedio")
public class Promedio extends Funcion{
	public Promedio(Indicador indicador){
		super(indicador);
	}
	
	public Promedio(){}
	
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo) {
		BigDecimal suma = sumatoria(empresa, periodo);
		BigDecimal[] resultado = new BigDecimal[1];
		resultado[0] = suma.divide(BigDecimal.valueOf(periodo));
		return resultado;
	}
}
