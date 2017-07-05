package model.metodologia;

import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

//Mismo indicador pero para otra empresa, optimizar el indicador dado
public class CondicionNoTaxativa extends Condicion{

	public CondicionNoTaxativa(Integer periodo, Indicador indicadorAOptimizar, BooleanCondition criterioComparacion) {
		super(periodo, indicadorAOptimizar, criterioComparacion);
		// TODO Auto-generated constructor stub
	}

	// TODO No taxativa, retorna al ganador
	public Empresa compararEmresas(Empresa empresaUno, Empresa empresaDos) {
		return null;
	}
}
