package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseYaExisteException;
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
		if(existeEmpresa(unaEmpresa.getNombre()))
			throw new EseYaExisteException("Una empresa con ese nombre ya existe");
		sobreescribirEmpresa(unaEmpresa);
	}
	
	public void sobreescribirEmpresa(Empresa unaEmpresa){
		transaction.begin();
		entityManager().persist(unaEmpresa);
		transaction.commit();
	}
	
	public Empresa obtenerEmpresa(String nombre){
		return obtenerEmpresas(nombre).get(0);
	}
	
	public Boolean existeEmpresa(String nombre){
		return obtenerEmpresas(nombre).size() != 0;
	}
	
	private List<Empresa> obtenerEmpresas(String nombre){
		@SuppressWarnings("unchecked")
		List<Empresa> empresas = entityManager()
				.createQuery("select empresa from Empresa as empresa where empresa.nombre = ?1")
				.setParameter(1, nombre)
				.getResultList();
		return empresas;
	}
}
