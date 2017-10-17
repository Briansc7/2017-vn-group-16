package model.repositories;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.Empresa;

public class RepositorioDeEmpresas extends Repositorio implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioDeEmpresas instance = new RepositorioDeEmpresas() ;
	
	private RepositorioDeEmpresas(){}
	
	public static RepositorioDeEmpresas getInstance(){
		return instance;
	}

	public Empresa buscarEmpresa(String nombre){
		return this.buscarUnoPorNombre(nombre);
	}

	@Override
	protected <T> String obtenerNombreDe(T t) {
		return ((Empresa)t).getNombre();
	}

	@Override
	public Class<Empresa> getTipo() {
		return Empresa.class;
	}
}
