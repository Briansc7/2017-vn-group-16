package model.metodologia;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.condiciones.BooleanCondition;

public class NoTaxativaLongevidad extends CondicionNoTaxativa {

	public NoTaxativaLongevidad(Integer periodo, String indicadorAOptimizar,
			BooleanCondition criterioComparacion, Integer pesoEstimado) {
		super(periodo, indicadorAOptimizar, criterioComparacion, pesoEstimado);
	}
	
	@Override
	public boolean compararEmpresas(Empresa empresaUno, Empresa empresaDos, BaseDeDatos baseDeDatos){
		return criterioComparacion.comparar(empresaUno.longevidad(), empresaDos.longevidad());
	}
}
