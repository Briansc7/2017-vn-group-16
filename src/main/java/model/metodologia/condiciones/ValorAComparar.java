package model.metodologia.condiciones;

import java.math.BigDecimal;

public class ValorAComparar {

	private BigDecimal valor;
	private BigDecimal[] valores;
	
	public ValorAComparar(BigDecimal valor){
		this.valor = valor;
	}
	
	public ValorAComparar(BigDecimal[] valores){
		this.valores = valores;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal[] getValores() {
		return valores;
	}
	
}
