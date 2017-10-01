package model.funciones;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
		List<BigDecimal> valoresDelPeriodo = new ArrayList<>();
		int anioActual = Year.now().getValue();
		
		for(int i=0; i<periodo; i++){
			try{
				valoresDelPeriodo.set(i, indicador.getValor(anioActual-i, empresa));
			} catch (Exception ex) {
				valoresDelPeriodo.set(i, BigDecimal.ZERO);
			} 
		}
		return valoresDelPeriodo;
	}
	
	protected BigDecimal sumatoria(Empresa empresa, Integer periodo){
		BigDecimal contador = BigDecimal.ZERO;
		
		for(int i=0; i<periodo; i++){
			contador = contador.add(indicador.getValor(periodo-i, empresa));
		}
		return contador;
	}
}
