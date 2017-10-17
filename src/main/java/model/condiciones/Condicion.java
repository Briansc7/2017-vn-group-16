package model.condiciones;

import java.util.List;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.funciones.Funcion;

@Observable
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_de_condicion")
@Table(name = "condiciones")
public abstract class Condicion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "periodo_de_evaluacion")
	protected Integer periodoDeEvaluacion;
	
	@ManyToOne( cascade = CascadeType.PERSIST)
	protected Funcion funcion;
	
	@Enumerated
	protected Comparador comparador;
	
	protected Condicion() {}
	
	public Condicion(Integer periodo, Funcion obtenerValor, Comparador comparador){
		this.periodoDeEvaluacion = periodo;
		this.funcion = obtenerValor;
		this.comparador = comparador;
	}
	
	public abstract List<Empresa> analizar(List<Empresa> empresas);
}
