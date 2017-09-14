package model.metodologia.condiciones;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;
import model.Empresa;
import model.funciones.Funcion;

@Observable
@Entity
@Table(name = "condicionComparativa")
public class CondicionComparativa extends CondicionGeneral{
	public CondicionComparativa(Integer periodo, Funcion obtenerValor, Comparador comparador){
		super(periodo, obtenerValor, comparador);
	}
	
	public List<Empresa> analizar(List<Empresa> empresas, BaseDeDatos baseDeDatos){
		return empresas.stream().sorted((empresa1, empresa2) -> compararEmpresas(empresa1, empresa2, baseDeDatos)).collect(Collectors.toList());
	}

	private int compararEmpresas(Empresa empresa1, Empresa empresa2, BaseDeDatos baseDeDatos) {
		Integer aux1=0,aux2=0;
		BigDecimal[] valores1 = obtenerValor.calcularValor(empresa1, periodoDeEvaluacion, baseDeDatos);
		BigDecimal[] valores2 = obtenerValor.calcularValor(empresa2, periodoDeEvaluacion, baseDeDatos);
		
		for(int i=0; i<periodoDeEvaluacion; i++){
			if(comparador.comparar(valores1[i], valores2[i]))
				aux1++;
			else
				aux2++;
		}
		return aux2.compareTo(aux1);
	}
}
