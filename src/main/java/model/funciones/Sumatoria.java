package model.funciones;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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
	public List<BigDecimal> calcularValor(Empresa empresa, Integer periodo) {
		return Arrays.asList(sumatoria(empresa, periodo));
	}
}
