package model.metodologia;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.condiciones.BooleanCondition;

//Mismo indicador pero para otra empresa, optimizar el indicador dado
public class CondicionNoTaxativa extends Condicion{
	Integer valorContra; //Valor contra el que se compara el indicador a optimizar
	String indicadorContra; //Nombre del indicador contra el que se compara el indicador a optimizar
	
	//Dos constructores, si se hace con un Ineger se sabe que es un valor contra el que se compara
	public CondicionNoTaxativa(Integer periodo, String indicadorAOptimizar,
			BooleanCondition criterioComparacion, Integer _valorContra) {
		
		super(periodo, indicadorAOptimizar, criterioComparacion);
		valorContra = _valorContra;
	}
	
	//Si se hace con un String se sabe que es otro indicador el que se usa
	public CondicionNoTaxativa(Integer periodo, String indicadorAOptimizar,
			BooleanCondition criterioComparacion, String _indicadorContra) {
		
		super(periodo, indicadorAOptimizar, criterioComparacion);
		indicadorContra = _indicadorContra;
	}

	// TODO No taxativa, retorna al ganador
	public boolean compararEmresas(Empresa empresaUno, Empresa empresaDos, BaseDeDatos baseDeDatos) {
		boolean resultado = true;
		for(int i = 0; i < periodo; i++){
			if(!(criterioComparacion.comprar(baseDeDatos.buscarIndicador(IndicadorAOptimizar).getValor(2017-i, empresaUno, baseDeDatos),  
					baseDeDatos.buscarIndicador(IndicadorAOptimizar).getValor(2017-i, empresaDos, baseDeDatos)))) resultado = false;
				
		}
		return resultado;
	}
}
