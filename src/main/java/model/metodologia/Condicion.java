package model.metodologia;

import model.metodologia.condiciones.BooleanCondition;

public class Condicion {
	private Integer periodo;//Cantidad de a�os hacia el pasado a los que se llega
	private String IndicadorAOptimizar;//Nombre del indicador aL que se le aplican las condiciones
	private BooleanCondition criterioComparacion;//>, <, >=, <=, ==
	
	public Condicion(Integer periodo, String indicadorAOptimizar, BooleanCondition criterioComparacion) {
		this.periodo = periodo;
		this.IndicadorAOptimizar = indicadorAOptimizar;
		this.criterioComparacion = criterioComparacion;
	}
}