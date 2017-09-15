package model.funciones;

import java.math.BigDecimal;

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

	@Override
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo) {
		BigDecimal[] valores = calcularValor(empresa, periodo);
		BigDecimal[] mediana = new BigDecimal[1];
		Integer indiceMediana;
		BigDecimal aux;
		
		if(periodo%2 == 0){
			indiceMediana = periodo/2;
			aux = valores[indiceMediana].add(valores[indiceMediana-1]);
			mediana[0] = aux.divide(aux);
		} else {
			indiceMediana = (periodo-1)/2;
			mediana[0] = valores[indiceMediana];
		}
		return mediana;
	}
}
