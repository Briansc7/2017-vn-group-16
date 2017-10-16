package model.repositories;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseNoExisteException;
import exceptions.EseYaExisteException;
import model.Indicador;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioDeIndicadores implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioDeIndicadores instance;

	public synchronized static RepositorioDeIndicadores getInstance(){
		if(instance == null)
			instance = new RepositorioDeIndicadores();
		return instance;
	}
	
	public void guardarIndicador(Indicador unIndicador){
		if(existeIndicador(unIndicador.getNombre()))
			throw new EseYaExisteException("Ya existe un indicador de nombre: " + unIndicador.getNombre());

		withTransaction(()->entityManager().persist(unIndicador));
	}
	
	//Este devuelve todos los indicadores de la base de datos
	public List<Indicador> obtenerIndicadores(){
		List<Indicador> indicadores = entityManager()
				.createQuery("select indicador from Indicador as indicador", Indicador.class)
				.getResultList();
		return indicadores;
	}
	
	public Indicador obtenerIndicador(String nombre){
		if(existeIndicador(nombre))
			return buscarIndicadores(nombre).get(0);
		throw new EseNoExisteException("No existe un indicador de nombre: " + nombre);
	}
	
	public Boolean existeIndicador(String nombre){
		return buscarIndicadores(nombre).size() != 0;
	}
	
	private List<Indicador> buscarIndicadores(String nombre){
		List<Indicador> indicadores = entityManager()
				.createQuery("select indicador from Indicador as indicador where indicador.nombre = ?1", Indicador.class)
				.setParameter(1, nombre)
				.getResultList();
		return indicadores;
	}
	
	public void guardarIndicadores(List<Indicador> indicadores) {
		indicadores.stream().forEach(indicador -> guardarIndicador(indicador));
	}

	public void removerIndicador(Indicador indicador) {
		withTransaction(()->entityManager().remove(indicador));
	}
}
