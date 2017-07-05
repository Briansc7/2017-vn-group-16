package componentesMatematicos;

import model.BaseDeDatos;
import model.Empresa;

public class IndicadorCalculo extends FactorLiteral{
	
	public IndicadorCalculo(String nombre){
		super(nombre);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		return unaBaseDeDatos.buscarIndicador(this.nombre).getValor(unPeriodo, unaEmpresa, unaBaseDeDatos);
	}
}
