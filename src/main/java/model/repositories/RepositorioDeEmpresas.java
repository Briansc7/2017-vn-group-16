package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import exceptions.EseNoExisteException;
import exceptions.EseYaExisteException;
import model.Empresa;

public class RepositorioDeEmpresas implements WithGlobalEntityManager, TransactionalOps {
	private static RepositorioDeEmpresas instance= new RepositorioDeEmpresas() ;
	
	private RepositorioDeEmpresas(){}
	
	public static RepositorioDeEmpresas getInstance(){
		return instance;
	}
	
	
	public void guardarEmpresa(Empresa unaEmpresa){
		if(existeEmpresa(unaEmpresa.getNombre()))
			throw new EseYaExisteException("Ya existe una empresa de nombre: " + unaEmpresa.getNombre());
		
		EntityTransaction transaction = entityManager().getTransaction();
		transaction.begin();
		entityManager().persist(unaEmpresa);
		transaction.commit();
	}
	
	//Este devuelve todas las empresas de la base de datos
	public List<Empresa> obtenerEmpresas(){
		return entityManager()
				.createQuery("select empresa from Empresa as empresa", Empresa.class)
				.getResultList();
	}
	
	public Empresa obtenerEmpresa(String nombre){
		if(existeEmpresa(nombre))
			return buscarEmpresas(nombre).get(0);
		throw new EseNoExisteException("No existe una empresa de nombre: " + nombre);
	}
	
	public Boolean existeEmpresa(String nombre){
		return buscarEmpresas(nombre).size() != 0;
	}
	
	private List<Empresa> buscarEmpresas(String nombre){
		@SuppressWarnings("unchecked")
		List<Empresa> empresas = entityManager()
				.createQuery("select empresa from Empresa as empresa where empresa.nombre = ?1")
				.setParameter(1, nombre)
				.getResultList();
		return empresas;
	}
	
	public void guardarEmpresas(List<Empresa> empresas) {
		empresas.stream().forEach(empresa -> guardarEmpresa(empresa));
	}
	

	public void removerEmpresa(Empresa empresa) {
		withTransaction(() ->  entityManager().remove(empresa));
	}

	public Empresa buscar(long id) {
		return entityManager()
				.createQuery("from Empresa as empresa where empresa.id = :id", Empresa.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
