package model.metodologia;

import org.uqbar.commons.utils.Observable;

import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

@Observable
public class Condicion {
	protected Integer periodo;//Cantidad de años hacia el pasado a los que se llega
	protected Indicador indicadorAOptimizar;//Nombre del indicador aL que se le aplican las condiciones
	protected BooleanCondition criterioComparacion;//>, <, >=, <=, ==
	
	public Condicion(Integer periodo, Indicador indicadorAOptimizar2, BooleanCondition criterioComparacion) {
		this.periodo = periodo;
		this.indicadorAOptimizar = indicadorAOptimizar2;
		this.criterioComparacion = criterioComparacion;
	}
	
	
}