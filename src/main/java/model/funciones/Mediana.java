package model.funciones;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;

public class Mediana extends Funcion{
	public Mediana(Indicador indicador){
		super(indicador);
	}

	@Override
	public BigDecimal[] calcularValor(Empresa empresa, Integer periodo, BaseDeDatos baseDeDatos) {
		BigDecimal[] valores = calcularValor(empresa, periodo, baseDeDatos);
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
