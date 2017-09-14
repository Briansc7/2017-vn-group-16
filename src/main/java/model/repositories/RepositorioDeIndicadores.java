package model.repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseNoExisteException;
import exceptions.EseYaExisteException;
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
		if(existeIndicador(unIndicador.getNombre()))
			throw new EseYaExisteException("Ya existe un indicador de nombre: " + unIndicador.getNombre());
		sobreescribirIndicador(unIndicador);
	}
	
	public void sobreescribirIndicador(Indicador unIndicador){
		transaction.begin();
		entityManager().persist(unIndicador);
		transaction.commit();
	}
	
	public Indicador obtenerIndicador(String nombre){
		return buscarIndicadores(nombre).get(0);
	}
	
	public Boolean existeIndicador(String nombre){
		return buscarIndicadores(nombre).size() != 0;
	}
	
	private List<Indicador> buscarIndicadores(String nombre){
		@SuppressWarnings("unchecked")
		List<Indicador> indicadores = entityManager()
		.createQuery("select indicador from Indicador as indicador where indicador.nombre = ?1")
		.setParameter(1, nombre)
		.getResultList();
		
		if(indicadores.size() == 0)
			throw new EseNoExisteException("No existe un indicador de nombre: " + nombre);
		
		return indicadores;
	}
}
