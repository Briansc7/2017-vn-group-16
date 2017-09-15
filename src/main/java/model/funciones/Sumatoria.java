package model.funciones;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;
import model.Indicador;

@Entity
@DiscriminatorValue("sumatoria")
public class Sumatoria extends Funcion{
	public Sumatoria(Indicador indicador){
		super(indicador);
	}
	
	public Sumatoria(){}
	
	@Override
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo) {
		BigDecimal[] resultado = {sumatoria(empresa, periodo)};
		return resultado;
	}
}
