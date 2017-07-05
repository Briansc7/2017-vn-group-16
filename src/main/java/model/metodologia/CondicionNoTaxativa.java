package model.metodologia;

import model.Empresa;
import model.Indicador;
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
	public Empresa compararEmresas(Empresa empresaUno, Empresa empresaDos) {
		return null;
	}
}
