package model.funciones;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;
import model.Indicador;

@Entity
@DiscriminatorValue("valoresDelPeriodo")
public class ValoresDelPeriodo extends Funcion{
	public ValoresDelPeriodo(Indicador indicador){
		super(indicador);
	}
	
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo){
		return calcularValoresDelPeriodo(empresa, periodo);
	}
}
