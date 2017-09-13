package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.Indicador;

public class RepositorioDeIndicadores implements WithGlobalEntityManager{
	private static RepositorioDeIndicadores instance;
	private EntityTransaction transaction = entityManager().getTransaction();

	public synchronized static RepositorioDeIndicadores getInstance(){
		if(instance == null)
			instance = new RepositorioDeIndicadores();
		return instance;
	}
	
	public void guardarIndicador(Indicador unIndicador){
		transaction.begin();
		entityManager().persist(unIndicador);
		transaction.commit();
	}
	
	public List<Indicador> obtenerIndicadores(String nombre){
		@SuppressWarnings("unchecked")
		List<Indicador> indicadores = entityManager()
				.createQuery("select indicador from Indicador as indicador where indicador.nombre = ?1")
				.setParameter(1, nombre)
				.getResultList();
		return indicadores;
	}
}
