package componentesMatematicos;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.BaseDeDatos;
import model.Empresa;

@Entity
@DiscriminatorValue("6")
public class FactorNumerico extends Factor {
	
	@Column(name = "valor")
	public BigDecimal valor;
	
	private FactorNumerico(){}
	
	public FactorNumerico(BigDecimal unValor){
		this.valor = unValor;
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		return valor;
	}
}
