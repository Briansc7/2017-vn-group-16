package model.funciones;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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
	
	public List<BigDecimal> calcularValor(Empresa empresa, Integer periodo) {
		BigDecimal suma = sumatoria(empresa, periodo);
		BigDecimal resultado;
		resultado = suma.divide(BigDecimal.valueOf(periodo));
		return Arrays.asList(resultado);
	}
}
