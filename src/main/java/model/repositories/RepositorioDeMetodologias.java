package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseNoExisteException;
import exceptions.EseYaExisteException;
import model.Metodologia;

public class RepositorioDeMetodologias implements WithGlobalEntityManager{
	private static RepositorioDeMetodologias instance;
	private EntityTransaction transaction = entityManager().getTransaction();
	
	//Singleton
	private RepositorioDeMetodologias(){}
	
	public synchronized static RepositorioDeMetodologias getInstance(){
		if(instance == null)
			instance = new RepositorioDeMetodologias();
		return instance;
	}
	
	public void guardarMetodologia(Metodologia unaMetodologia){
		if(existeMetodologia(unaMetodologia.getNombre()))
			throw new EseYaExisteException("Ya existe una metodologia de nombre: " + unaMetodologia.getNombre());
		transaction.begin();
		entityManager().persist(unaMetodologia);
		transaction.commit();
	}
	
	//Este devuelve todas las metodologias de la base de datos
	public List<Metodologia> obtenerMetodologias(){
		@SuppressWarnings("unchecked")
		List<Metodologia> metodologias = entityManager()
				.createQuery("from Metodologia")
				.getResultList();
		return metodologias;
	}
	
	public Metodologia obtenerMetodologia(String nombre){
		if(existeMetodologia(nombre))
			return buscarMetodologia(nombre).get(0);
		throw new EseNoExisteException("No existe una metodologia de nombre: " + nombre);
	}
	
	public Boolean existeMetodologia(String nombre){
		return buscarMetodologia(nombre).size() != 0;
	}
	
	public List<Metodologia> buscarMetodologia(String nombre){
		@SuppressWarnings("unchecked")
		List<Metodologia> metodologias = entityManager()
				.createQuery("select metodologia from Metodologia as metodologia where metodologia.nombre = :nombre")
				.setParameter("nombre", nombre)
				.getResultList();
		return metodologias;
	}

	public void guardarMetodologias(List<Metodologia> metodologias) {
		metodologias.stream().forEach(metodologia -> guardarMetodologia(metodologia));
	}
	

	public void removerMetodologia(Metodologia metodologia) {
		transaction.begin();
		entityManager().remove(metodologia);
		transaction.commit();
	}
	
}