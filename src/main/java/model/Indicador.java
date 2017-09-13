package model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

import componentesMatematicos.Expresion;
import parser.Parser;

@Observable
@Entity
@Table(name = "indicador")
public class Indicador extends Atributo{
	
	//parsear una sola vez, no N veces porque va a seguir dando lo mismo.
	//vuelve a parsear muchas veces cambiando el periodo una y otra vez
	//si empresa tiene 125 cuentas y 20 indicadores y los indicadores dependen de otros, esto se vuelve exponencial
	
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Expresion expresion;
	@Column(name = "formula")
	private String formula;
	
	@SuppressWarnings("unused")
	private Indicador(){super();}
	
	//
	public Indicador(String nombre, String formula) throws parser.ParseException, parser.TokenMgrError{
		super(nombre);
		this.formula = formula;
		this.expresion = Parser.parsear(formula);//2 parsers entonces logica repetida
		//parser componente que transforma estructura de datos en otra
		//segundo parser no es un parser, porque computa solamente
		//parser debe devolver un conjunto de objetos del dominio y que esos sepan calcular
	}
	
	public boolean esIdentico(String otroNombre, String otraFormula){
		return tieneElMismoNombre(otroNombre) && this.formula.equals(otraFormula);
	}
	
	public boolean tieneElMismoNombre(String otroNombre){
		return this.nombre.equals(otroNombre);
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		return this.expresion.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos);
	}
	
	public String getValorString(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos){
		String valorAuxiliar;
		try {
			valorAuxiliar = String.valueOf(this.getValor(unPeriodo, unaEmpresa, unaBaseDeDatos));
		} catch (Exception ex) {
			//valorAuxiliar = "*";
			valorAuxiliar = ex.getMessage();
		}
		return valorAuxiliar;
	}
	//empresa elegida es idea de vista, no existe este concepto en el modelo
	//singleton planilla, todos a la larga dependen de ese objeto. Entonces si tiene bug se rompe todo
	//todos acoplados a esto, si lo rompo explota el sistema
	//no necesitamos singleton y menos singleton con elementos de vista
	
	//no hay necesidad que el indicador conozca a la empresa pero si que lo use
	//conocer es tenerlo como atributo. Usar es recibirlo como parametro. Se usa cuando se necesita


	public String getNombre() {
		return nombre;
	}
	
	public String getFormula() {
		return formula;
	}
}

/*	
public boolean existePara(Empresa empresa, Integer periodo) {//FIXME : Chequear esto, para que se usa contenido
	return this.contenido.stream().allMatch(nombre -> this.existeComponente(nombre, empresa, periodo));
	
}

private boolean existeComponente(String nombre, Empresa empresa, Integer periodo){//FIXME : revisar instance de planilla
	if (nombre.substring(0,2).equalsIgnoreCase("c.")){
		return empresa.existeCuentaDel(nombre.substring(2), periodo);
	} else if (Planilla.instance.existeIndicador(nombre)){
		return Planilla.instance.buscarIndicador(nombre).existePara(empresa, periodo);
	}//problema preguntamos de que tipo es con if para que hagan cosas distintas
	//no hay polimorfismo porque faltan abstracciones, hay cosas que no estan separadas
	//falta abstraccion de algo que puede ser de tipo cuenta o tipo indicador
	//problema de nuevo acoplado con la vista
	//modelo, persistencia, vista, son 3 mundos distintos.
	//en el modelo estamos mezclando elementos de vista
	return false;
}
*/
