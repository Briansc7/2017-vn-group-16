package model.metodologia;

import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

public class Condicion {
	private Integer periodo;//Cantidad de años hacia el pasado a los que se llega
	private Indicador IndicadorAOptimizar;//Nombre del indicador aL que se le aplican las condiciones
	private BooleanCondition criterioComparacion;//>, <, >=, <=, ==
	
	public Condicion(Integer periodo, Indicador indicadorAOptimizar, BooleanCondition criterioComparacion) {
		this.periodo = periodo;
		this.IndicadorAOptimizar = indicadorAOptimizar;
		this.criterioComparacion = criterioComparacion;
	}
}