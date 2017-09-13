package model.metodologia.condiciones;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;
import model.Empresa;
import model.funciones.Funcion;

@Observable
public abstract class CondicionGeneral {
	protected Integer periodo;
	protected Funcion obtenerValor;
	protected Comparador comparador;
	//protected BaseDeDatos baseDeDatos = BaseDeDatosMock.getDatabaseInstance();//Esto luego hay que reemplazarlo por el repositorioS
	
	public CondicionGeneral(Integer periodo, Funcion obtenerValor, Comparador comparador){
		this.periodo = periodo;
		this.obtenerValor = obtenerValor;
		this.comparador = comparador;
	}
	
	public abstract List<Empresa> analizar(List<Empresa> empresas, BaseDeDatos baseDeDatos);
}
