package model.metodologia;

import java.math.BigDecimal;

import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

public class TaxativaLongevidad extends CondicionTaxativa {
	BigDecimal valorAComparar;
	
	public TaxativaLongevidad(Integer periodo, Indicador indicadorAOptimizar,
			BooleanCondition criterioComparacion, BigDecimal _valorAComparar) {
		super(periodo, indicadorAOptimizar, criterioComparacion);
		
		valorAComparar = _valorAComparar;
	}
	
	@Override
	public boolean aplicarCondicion(Empresa unaEmpresa){
		return criterioComparacion.comparar(unaEmpresa.longevidad(), valorAComparar);
	}
}
