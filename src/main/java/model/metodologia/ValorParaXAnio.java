package model.metodologia;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.ValorAComparar;

public class ValorParaXAnio implements Funcion{
	private Indicador indicador;
	
	public ValorParaXAnio(Indicador indicador){
		this.indicador = indicador;
	}
	
	public ValorAComparar calcularValor(Empresa empresa, Integer anio, BaseDeDatos baseDeDatos){
		return new ValorAComparar(indicador.getValor(anio, empresa, baseDeDatos));
	}
}
