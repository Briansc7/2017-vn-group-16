package model.metodologia;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import model.BaseDeDatos;
import model.Empresa;
import model.metodologia.condiciones.Comparador;

public class CondicionValorUnico extends CondicionGeneral{

	private BigDecimal valor;
	
	public CondicionValorUnico(Integer periodo, Funcion obtenerValor, Comparador comparador, BigDecimal valor){
		super(periodo, obtenerValor, comparador);
		this.valor = valor;
	}
	
	public List<Empresa> analizar(List<Empresa> empresas, BaseDeDatos baseDeDatos){
		return empresas.stream().filter(empresa -> filtrarEmpresa(empresa, baseDeDatos)).collect(Collectors.toList());
	}
	
	private boolean filtrarEmpresa(Empresa empresa, BaseDeDatos baseDeDatos){
		return comparador.comparar(obtenerValor.calcularValor(empresa, periodo, baseDeDatos)[0], valor);
	}
}