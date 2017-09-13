package componentesMatematicos;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.BaseDeDatos;
import model.Empresa;

@Entity
@DiscriminatorValue("3")
public class AtributoCalculo extends FactorLiteral{
	private AtributoCalculo(){
		super();
	}
	
	public AtributoCalculo(String nombre){
		super(nombre);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public BigDecimal getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		return unaBaseDeDatos.valorDe(this.nombre, unPeriodo, unaEmpresa);
		//return unaBaseDeDatos.buscarAtributo(this.nombre, unaEmpresa).getValor(unPeriodo, unaEmpresa, unaBaseDeDatos);
	}
}
