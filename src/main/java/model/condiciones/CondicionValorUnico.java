package model.condiciones;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.funciones.Funcion;

@Observable
@Entity
@DiscriminatorValue("valor_unico")
public class CondicionValorUnico extends Condicion {
	
	@Column(name = "valor_contra_el_que_comparar")
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
		return comparador.comparar(funcion.calcularValor(empresa, periodoDeEvaluacion).get(0), valorContraElQueComparar);
	}
}