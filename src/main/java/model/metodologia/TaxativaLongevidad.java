package model.metodologia;

import java.math.BigDecimal;

import model.BaseDeDatos;
import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

public class TaxativaLongevidad extends CondicionTaxativa {
	
	public TaxativaLongevidad(Integer periodo, Indicador indicadorAOptimizar,
			BooleanCondition criterioComparacion, BigDecimal valorAComparar) {
		super(periodo, indicadorAOptimizar, criterioComparacion, valorAComparar);
	}
	
	@Override
	public boolean aplicarCondicion(Empresa unaEmpresa, BaseDeDatos baseDeDatos) {
		return criterioComparacion.comparar(unaEmpresa.longevidad(), valorAComparar);
	}
}
