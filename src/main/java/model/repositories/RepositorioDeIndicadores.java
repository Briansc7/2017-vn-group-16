package model.repositories;

import model.Empresa;
import model.IndicadorAuxiliar;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Indicador;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeIndicadores extends Repositorio {

	private static RepositorioDeIndicadores instance = new RepositorioDeIndicadores();

	private RepositorioDeIndicadores(){}

	public static RepositorioDeIndicadores getInstance(){
		return instance;
	}

	public Indicador buscarIndicador(String nombre){
		return this.buscarUnoPorNombre(nombre);
	}

	@Override
	protected <T> String obtenerNombreDe(T t) {
		return ((Indicador)t).getNombre();
	}

	@Override
	public Class<Indicador> getTipo() {
		return Indicador.class;
	}

	public List<IndicadorAuxiliar> getIndicadoresAuxiliares(Empresa empresa, Integer periodo) {
		List<Indicador> indicadoresReales = this.buscarTodos();
		List<IndicadorAuxiliar> indicadoresAuxiliares = indicadoresReales.stream()
				.map(indicador -> new IndicadorAuxiliar(indicador.getNombre(), indicador.getValorString(periodo, empresa)))//FIXME a veces devuelve nullpointer ex
				.collect(Collectors.toList());
		return indicadoresAuxiliares;
	}
}
