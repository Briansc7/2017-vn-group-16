package model;

import java.math.BigDecimal;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

import componentesMatematicos.Expresion;
import parser.Parser;

@Observable
@Entity
@DiscriminatorValue("indicador")
public class Indicador extends Atributo{
	
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Expresion expresion;

	@Column(name = "formula")
	private String formula;
	
	@ManyToOne( cascade = CascadeType.PERSIST)
	private Long usuario_id;

	@SuppressWarnings("unused")
	private Indicador(){super();}
	
	//
	public Indicador(String nombre, String formula) throws parser.ParseException, parser.TokenMgrError{
		super(nombre);
		this.formula = formula;
		this.expresion = Parser.parsear(formula);
	}
	
	public boolean esIdentico(String otroNombre, String otraFormula){
		return tieneElMismoNombre(otroNombre) && this.formula.equals(otraFormula);
	}
	
	public boolean tieneElMismoNombre(String otroNombre){
		return this.nombre.equals(otroNombre);
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa) {
		return this.expresion.getValor(unPeriodo, unaEmpresa);
	}
	
	public String getValorString(Integer unPeriodo, Empresa unaEmpresa){
		String valorAuxiliar;
		try {
			valorAuxiliar = String.valueOf(this.getValor(unPeriodo, unaEmpresa));
		} catch (Exception ex) {
			valorAuxiliar = ex.getMessage();
		}
		return valorAuxiliar;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getFormula() {
		return formula;
	}

	public boolean seLlama(String nombre) {
		return this.nombre.equalsIgnoreCase(nombre);
	}
	
	@Override
	public String toString(){
		return nombre;
	}
}
