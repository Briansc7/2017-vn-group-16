package model.funciones;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.Empresa;
import model.Indicador;

@Entity
@DiscriminatorValue("mediana")
public class Mediana extends Funcion{
	public Mediana(Indicador indicador){
		super(indicador);
	}
	
	public Mediana(){}
	
	@Override
	public List<BigDecimal> calcularValor(Empresa empresa, Integer periodo) {
		List<BigDecimal> valores = calcularValoresDelPeriodo(empresa, periodo);
		Collections.sort(valores);
		BigDecimal mediana;
		Integer indiceMediana;
		BigDecimal sumaAux;
		
		if(periodo%2 == 0){
			indiceMediana = periodo/2;
			sumaAux = valores.get(indiceMediana).add(valores.get(indiceMediana-1));
			mediana = sumaAux.divide(new BigDecimal(2));
		} else {
			indiceMediana = (periodo-1)/2;
			mediana = valores.get(indiceMediana);
		}
		return Arrays.asList(mediana);
	}
}
