package model.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseNoExisteException;
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
			throw new EseYaExisteException("Ya existe una empresa de nombre: " + unaEmpresa.getNombre());
		sobreescribirEmpresa(unaEmpresa);
	}
	
	public void sobreescribirEmpresa(Empresa unaEmpresa){
		transaction.begin();
		entityManager().persist(unaEmpresa);
		transaction.commit();
	}
	
	public List<Empresa> obtenerEmpresas(String nombre){
		return new ArrayList<Empresa>();
	}
	
	public Empresa obtenerEmpresa(String nombre){
		return buscarEmpresas(nombre).get(0);
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
		
		if(empresas.size() == 0)
			throw new EseNoExisteException("No existe una empresa de nombre: " + nombre);
		
		return empresas;
	}
}
