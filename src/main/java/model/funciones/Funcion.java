package model.funciones;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.collect.Ordering;
import model.Cuenta;
import model.Empresa;
import model.Indicador;

@Entity
@Table(name = "funciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeFuncion")
public abstract class Funcion {
	
	@Id
	@GeneratedValue
	//@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	protected Indicador indicador;
	
	public Funcion(Indicador indicador){
		this.indicador = indicador;
	}
	
	public Funcion(){}
	
	public abstract List<BigDecimal> calcularValor(Empresa empresa, Integer periodo);
	
	public void setIndicador(Indicador indicador){
		this.indicador = indicador;
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}

	public boolean seLlama(String nombre) {
		return this.getClass().getSimpleName().equalsIgnoreCase(nombre);
	}
	
	protected List<BigDecimal> calcularValoresDelPeriodo(Empresa empresa, Integer periodo) {
		Integer anioActual = Year.now().getValue();
		List<Integer> anios = IntStream.range(anioActual-periodo+1, anioActual+1).boxed().collect(Collectors.toList());

		return anios.stream().map((anio) ->this.calcularValorIndicador(empresa, anio)).collect(Collectors.toList());
	}

	protected BigDecimal calcularValorIndicador(Empresa empresa, Integer anio){
		try{
			return indicador.getValor(anio, empresa);
		} catch (Exception ex) {
			return BigDecimal.ZERO;
		}
	}

	protected BigDecimal sumatoria(Empresa empresa, Integer periodo){
		return this.calcularValoresDelPeriodo(empresa, periodo)
				.stream().reduce(BigDecimal.ZERO, (valor1, valor2) -> valor1.add(valor2));
	}

	// el valor mas reciente esta primero
	protected boolean esCreciente(List<BigDecimal> valores) {
		return Ordering.natural().reverse().isOrdered(valores);
	}

	protected boolean esDecreciente(List<BigDecimal> valores) {
		return Ordering.natural().isOrdered(valores);
	}
}
