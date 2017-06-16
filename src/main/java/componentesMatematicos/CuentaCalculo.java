package componentesMatematicos;

import model.Empresa;
import model.Planilla;

public class CuentaCalculo extends FactorLiteral{
	
	public CuentaCalculo(String nombre){
		super(nombre);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, Planilla unaPlanilla) {
		return unaEmpresa.buscarCuenta(this.nombre, unPeriodo).getValor();
	}
	

}
