package model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import componentesMatematicos.Expresion;
import exceptions.NoExisteAtributoException;
import parser.Parser;

@Entity
@DiscriminatorValue("indicador")
public class Indicador extends Atributo{
	
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Expresion expresion;

	@Column(name = "formula")
	private String formula;
	
	@ManyToOne( cascade = CascadeType.PERSIST)
	protected Usuario usuario;

	@SuppressWarnings("unused")
	private Indicador(){super();}
	
	//
	public Indicador(String nombre, String formula) throws parser.ParseException, parser.TokenMgrError{
		super(nombre);
		this.formula = formula;
		this.expresion = Parser.parsear(formula);
	}
	
	public Indicador(String nombre, String formula, Usuario usuario) throws parser.ParseException{
		super(nombre);
		this.formula = formula;
		this.expresion = Parser.parsear(formula);
		this.usuario = usuario;
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
		} catch (NoExisteAtributoException ex) {
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

	public long getIdUsuario() {
		return usuario.getId();
	}
	public boolean seLlama(String nombre) {
		return this.nombre.equalsIgnoreCase(nombre);
	}
	
	@Override
	public String toString(){
		return nombre;
	}
}
