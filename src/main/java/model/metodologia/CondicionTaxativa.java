package model.metodologia;

import java.util.List;
import java.util.stream.Collectors;

import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

//Comparar contra una constante u otro indicador de la misma empresa
public class CondicionTaxativa extends Condicion {

	public CondicionTaxativa(Integer periodo, Indicador indicadorAOptimizar,
			BooleanCondition criterioComparacion) {

		super(periodo, indicadorAOptimizar, criterioComparacion);
	}
	
	@Override
	public List<Empresa> filtrar(List<Empresa> empresas) {
		return empresas.stream().filter(empresa -> this.aplicarCondicion(empresa)).collect(Collectors.toList());
	}

	// TODO Retorna true si la empresa cumple la condicion
	public boolean aplicarCondicion(Empresa unaEmpresa) {
		boolean resultado = true;
		for(int i = 0; i < periodo-1; i++){
			if(!(criterioComparacion.comparar(baseDeDatos.valorDe(indicadorAOptimizar.getNombre(), 2017-i, unaEmpresa),
					baseDeDatos.valorDe(indicadorAOptimizar.getNombre(), 2017-(i+1), unaEmpresa))))
				resultado = false;
		}
		return resultado;
	}
}