package componentesMatematicos;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.BaseDeDatos;
import model.Empresa;

@Entity
@DiscriminatorValue("3")
public class AtributoCalculo extends FactorLiteral{
	@SuppressWarnings("unused")
	private AtributoCalculo(){
		super();
	}
	
	public AtributoCalculo(String nombre){
		super(nombre);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa) {
		return new CalculoDeIndicador().valorDe(this.nombre, unPeriodo, unaEmpresa);
	}
}
