package componentesMatematicos;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;

public class FactorNumerico extends Factor {
	
	public BigDecimal valor;
	
	public FactorNumerico(BigDecimal unValor){
		this.valor = unValor;
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		return valor;
	}
}
