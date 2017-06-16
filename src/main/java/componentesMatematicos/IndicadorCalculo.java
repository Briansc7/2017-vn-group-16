package componentesMatematicos;

import model.Empresa;
import model.Planilla;

public class IndicadorCalculo extends FactorLiteral{
	
	public IndicadorCalculo(String nombre){
		super(nombre);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getValor(Integer unPeriodo, Empresa unaEmpresa, Planilla unaPlanilla) {
		return unaPlanilla.buscarIndicador(this.nombre).getValor(unPeriodo, unaEmpresa, unaPlanilla);
	}
}
