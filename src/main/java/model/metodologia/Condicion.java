package model.metodologia;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import mockObjects.BaseDeDatosMock;
import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

@Observable
public abstract class Condicion{
	protected Integer periodo;//Cantidad de años hacia el pasado a los que se llega
	protected Indicador indicadorAOptimizar;//Nombre del indicador aL que se le aplican las condiciones
	protected BooleanCondition criterioComparacion;//>, <, >=, <=, ==
	protected BaseDeDatos baseDeDatos = BaseDeDatosMock.getDatabaseInstance();//Esto luego hay que reemplazarlo por el repositorioS
	
	public Condicion(Integer periodo, Indicador indicadorAOptimizar, BooleanCondition criterioComparacion) {
		this.periodo = periodo;
		this.indicadorAOptimizar = indicadorAOptimizar;
		this.criterioComparacion = criterioComparacion;
	}
	
	public abstract List<Empresa> filtrar(List<Empresa> empresas);
	
}