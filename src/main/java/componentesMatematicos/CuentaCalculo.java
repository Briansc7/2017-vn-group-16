package componentesMatematicos;

import model.BaseDeDatos;
import model.Empresa;

public class CuentaCalculo extends FactorLiteral{
	
	public CuentaCalculo(String nombre){
		super(nombre);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		return unaEmpresa.buscarCuenta(this.nombre, unPeriodo).getValor();
	}
	

}
