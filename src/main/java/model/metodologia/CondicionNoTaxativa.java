package model.metodologia;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.condiciones.BooleanCondition;

//Mismo indicador pero para otra empresa, optimizar el indicador dado
public class CondicionNoTaxativa extends Condicion{
	private Integer pesoEstimado;
	
	public CondicionNoTaxativa(Integer periodo, String indicadorAOptimizar,
			BooleanCondition criterioComparacion, Integer pesoEstimado) {
		super(periodo, indicadorAOptimizar, criterioComparacion);
		this.pesoEstimado = pesoEstimado;
	}

	// TODO No taxativa, retorna al ganador
	public boolean compararEmpresas(Empresa empresaUno, Empresa empresaDos, BaseDeDatos baseDeDatos) {
		boolean resultado = true;
		for(int i = 0; i < periodo; i++){
			if(!(criterioComparacion.comparar(baseDeDatos.valorDe(indicadorAOptimizar, 2017-i, empresaUno),  
					baseDeDatos.valorDe(indicadorAOptimizar, 2017-i, empresaDos))))
				resultado = false;
		}
		return resultado;
	}
	
	public Integer getPesoEstimado() {
		return pesoEstimado;
	}
}
