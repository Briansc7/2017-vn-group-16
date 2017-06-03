package model;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import calculadora.Calculadora;
import calculadora.ParseException;
import calculadora.TokenMgrError;
import parser.ParserTP;

@Observable
public class Indicador {
	//indicador debe ser get valor en cierto año y de cierta empresa
	
	//parsear una sola vez, no N veces porque va a seguir dando lo mismo.
	//vuelve a parsear muchas veces cambiando el periodo una y otra vez
	//si empresa tiene 125 cuentas y 20 indicadores y los indicadores dependen de otros, esto se vuelve exponencial
	private String nombre;
	private String expresion;
	private List<String> contenido;
	
	public Indicador(String nombre, String expresion) throws parser.ParseException, parser.TokenMgrError{
		this.nombre = nombre;
		this.expresion = expresion;
		this.contenido = ParserTP.parsear(this.expresion);//2 parsers entonces logica repetida
		//parser componente que transforma estructura de datos en otra
		//segundo parser no es un parser, porque computa solamente
		//parser debe devolver un conjunto de objetos del dominio y que esos sepan calcular
	}
	
	public int getValor() throws NumberFormatException, ParseException, TokenMgrError {
		return Calculadora.calcular(this.expresion, Planilla.instance.getEmpresaElegida(), Planilla.instance.getPeriodoElegido());
	}//empresa elegida es idea de vista, no existe este concepto en el modelo
	//singleton planilla, todos a la larga dependen de ese objeto. Entonces si tiene bug se rompe todo
	//todos acoplados a esto, si lo rompo explota el sistema
	//no necesitamos singleton y menos singleton con elementos de vista
	
	//no hay necesidad que el indicador conozca a la empresa pero si que lo use
	//conocer es tenerlo como atributo. Usar es recibirlo como parametro. Se usa cuando se necesita
	
	public boolean existePara(Empresa empresa, Integer periodo) {
		return this.contenido.stream().allMatch(nombre -> this.existeComponente(nombre, empresa, periodo));
		
	}
	
	private boolean existeComponente(String nombre, Empresa empresa, Integer periodo){
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
	
	public String getNombre() {
		return nombre;
	}
}
