package testMetodologia;

import java.math.BigDecimal;

import model.metodologia.CondicionComparativa;
import model.metodologia.CondicionGeneral;
import model.metodologia.CondicionValorUnico;
import model.metodologia.Funcion;
import model.metodologia.condiciones.Comparador;

public class CondicionConstructor {
	private Integer periodo;
	private Funcion obtenerValor;
	private Comparador comparador;
	private BigDecimal valor;
	
	public CondicionConstructor periodoDeEvaluacion(Integer periodo){
		this.periodo = periodo;
		return this;
	}
	
	public CondicionConstructor funcionParaObtenerValor(Funcion obtenerValor){
		this.obtenerValor = obtenerValor;
		return this;
	}

	public CondicionConstructor comparador(Comparador comparador){
		this.comparador = comparador;
		return this;
	}
	
	public CondicionConstructor valor(BigDecimal valor){
		this.valor = valor;
		return this;
	}
	
	public CondicionGeneral build(){
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
