package builders;

import java.math.BigDecimal;

import model.funciones.Funcion;
import model.condiciones.Comparador;
import model.condiciones.CondicionComparativa;
import model.condiciones.Condicion;
import model.condiciones.CondicionValorUnico;

public class CondicionBuilder {
	private Integer periodo;
	private Funcion obtenerValor;
	private Comparador comparador;
	private BigDecimal valor;
	
	public CondicionBuilder periodoDeEvaluacion(Integer periodo){
		this.periodo = periodo;
		return this;
	}
	
	public CondicionBuilder funcionParaObtenerValor(Funcion obtenerValor){
		this.obtenerValor = obtenerValor;
		return this;
	}

	public CondicionBuilder comparador(Comparador comparador){
		this.comparador = comparador;
		return this;
	}
	
	public CondicionBuilder valorContraElQueSeCompara(BigDecimal valor){
		this.valor = valor;
		return this;
	}
	
	public Condicion build(){
		if(periodo == null)
			throw new RuntimeException("falta periodo");
		else if(obtenerValor == null)
			throw new RuntimeException("falta funcion");
		else if(comparador == null)
			throw new RuntimeException("falta comparador");
		else if(valor == null)
			return new CondicionComparativa(periodo, obtenerValor, comparador);
		return new CondicionValorUnico(periodo, obtenerValor, comparador, valor);
	}
}
