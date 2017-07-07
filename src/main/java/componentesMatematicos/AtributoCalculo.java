package componentesMatematicos;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;

public class AtributoCalculo extends FactorLiteral{
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
