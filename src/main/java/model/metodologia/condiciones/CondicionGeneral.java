package model.metodologia.condiciones;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.funciones.Funcion;

@Observable
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeCondicion")
public abstract class CondicionGeneral {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "periodoDeEvaluacion")
	protected Integer periodoDeEvaluacion;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	//@Column(name = "funcion")
	protected Funcion funcion;
	
	@Enumerated
	protected Comparador comparador;
	//protected BaseDeDatos baseDeDatos = BaseDeDatosMock.getDatabaseInstance();//Esto luego hay que reemplazarlo por el repositorioS
	
	protected CondicionGeneral() {}
	
	public CondicionGeneral(Integer periodo, Funcion obtenerValor, Comparador comparador){
		this.periodoDeEvaluacion = periodo;
		this.funcion = obtenerValor;
		this.comparador = comparador;
	}
	
	public abstract List<Empresa> analizar(List<Empresa> empresas);
}
