package model.repositories;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Indicador;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioDeIndicadores extends Repositorio implements WithGlobalEntityManager, TransactionalOps {

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
}
