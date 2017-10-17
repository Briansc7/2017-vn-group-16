package model.condiciones;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.funciones.Funcion;

@Observable
@Entity
@DiscriminatorValue("comparativa")
public class CondicionComparativa extends Condicion {
	
	protected CondicionComparativa() {}
	
	public CondicionComparativa(Integer periodo, Funcion obtenerValor, Comparador comparador){
		super(periodo, obtenerValor, comparador);
	}
	
	public List<Empresa> analizar(List<Empresa> empresas){
		return empresas.stream().sorted((empresa1, empresa2) -> compararEmpresas(empresa1, empresa2)).collect(Collectors.toList());
	}

	private int compararEmpresas(Empresa empresa1, Empresa empresa2) {
		Integer aux1=0,aux2=0;
		List<BigDecimal> valores1 = funcion.calcularValor(empresa1, periodoDeEvaluacion);
		List<BigDecimal> valores2 = funcion.calcularValor(empresa2, periodoDeEvaluacion);
		
		for(int i=0; i<periodoDeEvaluacion; i++){
			if(comparador.comparar(valores1.get(i), valores2.get(i)))
				aux1++;
			else
				aux2++;
		}
		return aux2.compareTo(aux1);
	}
}
