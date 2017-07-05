package model.metodologia;

import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

//Comparar contra una constante u otro indicador de la misma empresa
public class CondicionTaxativa extends Condicion{
	private Integer valorAComparar;//Depende del tipo de comparacion, es la constante
	private Indicador IndicadorAComparar;//Nombre de otro con el que se compara el que hay que optimizar
	
	//TODO ¿Debería separar la que tiene constante de la de indicador?
	public CondicionTaxativa(Integer periodo, Indicador indicadorAOptimizar, BooleanCondition criterioComparacion) {
		super(periodo, indicadorAOptimizar, criterioComparacion);
		
	}
	
	// TODO Retorna true si la empresa cumple la condicion
	public boolean aplicarCondicion(Empresa unaEmpresa) {
		return false;
	}
}