package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Empresa;

public class RepositorioDeEmpresas implements WithGlobalEntityManager{
	private static RepositorioDeEmpresas instance;
	private EntityTransaction transaction = entityManager().getTransaction();
	
	private RepositorioDeEmpresas(){}
	
	public synchronized static RepositorioDeEmpresas getInstance(){
		if(instance == null)
			instance = new RepositorioDeEmpresas();
		return instance;
	}
	
	public void guaradarEmpresa(Empresa unaEmpresa){
		transaction.begin();
		entityManager().persist(unaEmpresa);
		transaction.commit();
	}
	
	public List<Empresa> obtenerEmpresas(String nombre){
		@SuppressWarnings("unchecked")
		List<Empresa> empresas = entityManager()
				.createQuery("select empresa from Empresa as empresa where empresa.nombre = ?1")
				.setParameter(1, nombre)
				.getResultList();
		return empresas;
	}
}
