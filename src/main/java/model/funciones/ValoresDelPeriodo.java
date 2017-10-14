package model.funciones;

import java.math.BigDecimal;
import java.util.List;

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
	
	protected ValoresDelPeriodo(){}
	
	public List<BigDecimal> calcularValor(Empresa empresa, Integer periodo){
		return this.calcularValoresDelPeriodo(empresa, periodo);
	}
}
