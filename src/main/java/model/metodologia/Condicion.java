package model.metodologia;

import model.metodologia.condiciones.BooleanCondition;

public class Condicion {
	protected Integer periodo;//Cantidad de años hacia el pasado a los que se llega
	protected String indicadorAOptimizar;//Nombre del indicador aL que se le aplican las condiciones
	protected BooleanCondition criterioComparacion;//>, <, >=, <=, ==
	
	public Condicion(Integer periodo, String indicadorAOptimizar, BooleanCondition criterioComparacion) {
		this.periodo = periodo;
		this.indicadorAOptimizar = indicadorAOptimizar;
		this.criterioComparacion = criterioComparacion;
	}
}