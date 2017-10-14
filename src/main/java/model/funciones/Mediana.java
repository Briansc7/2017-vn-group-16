package model.funciones;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.google.common.math.Quantiles;
import model.Empresa;
import model.Indicador;

@Entity
@DiscriminatorValue("mediana")
public class Mediana extends Funcion{

	public Mediana(Indicador indicador){
		super(indicador);
	}
	
	protected Mediana(){}
	
	@Override
	public List<BigDecimal> calcularValor(Empresa empresa, Integer periodo) {
		List<BigDecimal> valores = this.calcularValoresDelPeriodo(empresa, periodo);
		this.quitarCeros(valores);
		Collections.sort(valores);
		BigDecimal mediana = new BigDecimal(Quantiles.median().compute(valores));

		return Arrays.asList(mediana);
	}
}
