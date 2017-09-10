package model.metodologia;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.Indicador;
import model.metodologia.condiciones.BooleanCondition;

//Mismo indicador pero para otra empresa, optimizar el indicador dado
@Observable
public class CondicionNoTaxativa extends Condicion{
	
	public CondicionNoTaxativa(Integer periodo, Indicador indicadorAOptimizar,
			BooleanCondition criterioComparacion) {
		super(periodo, indicadorAOptimizar, criterioComparacion);
	}
	
	@Override
	public List<Empresa> filtrar(List<Empresa> empresas) {
		empresas.sort((empresa1, empresa2) -> compararEmpresas(empresa1, empresa2));
		return empresas;
	}

	// TODO No taxativa, retorna al ganador
	private int compararEmpresas(Empresa empresaUno, Empresa empresaDos) {
		int resultado = -1;
		for(int i = 0; i < periodo; i++){
			if(!(criterioComparacion.comparar(baseDeDatos.valorDe(indicadorAOptimizar.getNombre(), 2017-i, empresaUno),  
					baseDeDatos.valorDe(indicadorAOptimizar.getNombre(), 2017-i, empresaDos))))
				resultado = 1;
		}
		return resultado;
	}
	
	@Override
	public String toString(){
		return "Periodo: "+periodo+" Indicador: "+indicadorAOptimizar;
	}
}
