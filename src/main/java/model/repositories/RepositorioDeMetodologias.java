package model.repositories;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseNoExisteException;
import exceptions.EseYaExisteException;
import model.Metodologia;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioDeMetodologias implements WithGlobalEntityManager, TransactionalOps{

	private static RepositorioDeMetodologias instance = new RepositorioDeMetodologias();

	//Singleton
	private RepositorioDeMetodologias(){}
	
	public static RepositorioDeMetodologias getInstance(){
		return instance;
	}
	
	public void guardarMetodologia(Metodologia unaMetodologia){
		if(existeMetodologia(unaMetodologia.getNombre()))
			throw new EseYaExisteException("Ya existe una metodologia de nombre: " + unaMetodologia.getNombre());
		withTransaction(()->entityManager().persist(unaMetodologia));
	}
	
	//Este devuelve todas las metodologias de la base de datos
	public List<Metodologia> obtenerMetodologias(){
		List<Metodologia> metodologias = entityManager()
				.createQuery("from Metodologia", Metodologia.class)
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
		List<Metodologia> metodologias = entityManager()
				.createQuery("from Metodologia as metodologia where metodologia.nombre = :nombre", Metodologia.class)
				.setParameter("nombre", nombre)
				.getResultList();
		return metodologias;
	}

	public void guardarMetodologias(List<Metodologia> metodologias) {
		metodologias.stream().forEach(metodologia -> guardarMetodologia(metodologia));
	}

	public void removerMetodologia(Metodologia metodologia) {
		withTransaction(() -> entityManager().remove(metodologia));
	}
}