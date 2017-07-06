package model.metodologia;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

//Comparar contra una constante u otro indicador de la misma empresa
public class CondicionTaxativa extends Condicion{
	private Integer valorAComparar;//Depende del tipo de comparacion, es la constante
	private String indicadorAComparar;//Nombre de otro con el que se compara el que hay que optimizar
	
	//Dos constructores, si se hace con un Ineger se sabe que es un valor contra el que se compara
		public CondicionTaxativa(Integer periodo, String indicadorAOptimizar,
				BooleanCondition criterioComparacion, Integer _valorAComparar) {
			
			super(periodo, indicadorAOptimizar, criterioComparacion);
			valorAComparar = _valorAComparar;
		}
		
		//Si se hace con un String se sabe que es otro indicador el que se usa
		public CondicionTaxativa(Integer periodo, String indicadorAOptimizar,
				BooleanCondition criterioComparacion, String _indicadorAComparar) {
			
			super(periodo, indicadorAOptimizar, criterioComparacion);
			indicadorAComparar = _indicadorAComparar;
		}
	
	// TODO Retorna true si la empresa cumple la condicion
	public boolean aplicarCondicion(Empresa unaEmpresa, BaseDeDatos baseDeDatos) {
		boolean resultado = true;
		for(int i = 0; i < periodo-1; i++){
			if(!(criterioComparacion.comparar(baseDeDatos.valorDe(indicadorAOptimizar, 2017-i, unaEmpresa), baseDeDatos.valorDe(indicadorAOptimizar, 2017-(i+1), unaEmpresa))))
				resultado = false;
		}
		return resultado;
	}
}