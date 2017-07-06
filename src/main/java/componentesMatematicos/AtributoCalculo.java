package componentesMatematicos;

import model.BaseDeDatos;
import model.Empresa;

public class AtributoCalculo extends FactorLiteral{
	public AtributoCalculo(String nombre){
		super(nombre);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, BaseDeDatos unaBaseDeDatos) {
		if(unaBaseDeDatos.existeIndicador(nombre)){
			return unaBaseDeDatos.buscarIndicador(nombre).getValor(unPeriodo, unaEmpresa, unaBaseDeDatos);
		} else {//if(unaEmpresa.existeCuentaDel(nombre, unPeriodo)){
			return unaEmpresa.buscarCuenta(nombre, unPeriodo).getValor();
		}
		//return unaBaseDeDatos.getValorDe(this.nombre, unPeriodo, unaEmpresa);
		//return unaBaseDeDatos.buscarAtributo(this.nombre, unaEmpresa).getValor(unPeriodo, unaEmpresa, unaBaseDeDatos);
	}
}
