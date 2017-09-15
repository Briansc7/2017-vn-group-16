package model.metodologia.condiciones;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

import model.BaseDeDatos;
import model.Empresa;
import model.funciones.Funcion;

@Observable
@Entity
@Table(name = "condicionValorUnico")
public class CondicionValorUnico extends CondicionGeneral{
	
	@Column(name = "valorContraElQueComparar")
	private BigDecimal valorContraElQueComparar;
	
	protected CondicionValorUnico() {}
	
	public CondicionValorUnico(Integer periodo, Funcion obtenerValor, Comparador comparador, BigDecimal valor){
		super(periodo, obtenerValor, comparador);
		this.valorContraElQueComparar = valor;
	}
	
	public List<Empresa> analizar(List<Empresa> empresas){
		return empresas.stream().filter(empresa -> filtrarEmpresa(empresa)).collect(Collectors.toList());
	}
	
	private boolean filtrarEmpresa(Empresa empresa){
		return comparador.comparar(obtenerValor.calcularValor(empresa, periodoDeEvaluacion)[0], valorContraElQueComparar);
	}
}