package model.metodologia;

import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

public class NoTaxativaLongevidad extends CondicionNoTaxativa {

	public NoTaxativaLongevidad(Integer periodo, Indicador indicadorAOptimizar,
			BooleanCondition criterioComparacion) {
		super(periodo, indicadorAOptimizar, criterioComparacion);
	}
	
	@Override
	public int compararEmpresas(Empresa empresaUno, Empresa empresaDos){
		if(criterioComparacion.comparar(empresaUno.longevidad(), empresaDos.longevidad()))
			return -1;
		return 1;
	}
}
