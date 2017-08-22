package model.metodologia.condiciones;

import java.math.BigDecimal;
import java.util.List;

public class ValorAComparar {

	private BigDecimal valor;
	private List<BigDecimal> valores;
	
	public ValorAComparar(BigDecimal valor){
		this.valor = valor;
	}
	
	public ValorAComparar(List<BigDecimal> valores){
		this.valores = valores;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public List<BigDecimal> getValores() {
		return valores;
	}
	
}
