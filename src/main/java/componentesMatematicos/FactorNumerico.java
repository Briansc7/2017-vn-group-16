package componentesMatematicos;

import java.time.Year;

import model.Empresa;

public class FactorNumerico extends Factor {
	
	public Integer valor;
	
	public FactorNumerico(Integer unValor){
		this.valor = unValor;
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa) {
		return valor;
	}
}
