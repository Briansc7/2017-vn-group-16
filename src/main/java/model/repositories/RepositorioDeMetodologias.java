package model.repositories;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Metodologia;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioDeMetodologias extends Repositorio implements WithGlobalEntityManager, TransactionalOps{

	private static RepositorioDeMetodologias instance = new RepositorioDeMetodologias();

	//Singleton
	private RepositorioDeMetodologias(){}
	
	public static RepositorioDeMetodologias getInstance(){
		return instance;
	}

	public Metodologia buscarMetodologia(String nombre){
		return this.buscarUnoPorNombre(nombre);
	}

	@Override
	protected <T> String obtenerNombreDe(T t) {
		return ((Metodologia)t).getNombre();
	}

	@Override
	public Class<Metodologia> getTipo() {
		return Metodologia.class;
	}
}