package model.repositories;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import exceptions.EseNoExisteException;
import model.Indicador;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioDeIndicadores extends Repositorio implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioDeIndicadores instance = new RepositorioDeIndicadores();

	private RepositorioDeIndicadores(){}

	public static RepositorioDeIndicadores getInstance(){
		return instance;
	}
	
//	public void guardarIndicador(Indicador unIndicador){
//		if(existeIndicador(unIndicador.getNombre()))
//			throw new EseYaExisteException("Ya existe un indicador de nombre: " + unIndicador.getNombre());
//
//		withTransaction(()->entityManager().persist(unIndicador));
//	}
	
	//Este devuelve todos los indicadores de la base de datos
//	public List<Indicador> obtenerIndicadores(){
//		List<Indicador> indicadores = entityManager()
//				.createQuery("select indicador from Indicador as indicador", Indicador.class)
//				.getResultList();
//		return indicadores;
//	}
	
	public Indicador obtenerIndicador(String nombre){
//		if(existeIndicador(nombre))
//			return buscarIndicadores(nombre).get(0);
//		throw new EseNoExisteException("No existe un indicador de nombre: " + nombre);
		return this.buscarUnoPorNombre(nombre);
	}
	
//	public Boolean existeIndicador(String nombre){
//		return buscarIndicadores(nombre).size() != 0;
//	}
	
//	private List<Indicador> buscarIndicadores(String nombre){
//		List<Indicador> indicadores = entityManager()
//				.createQuery("select indicador from Indicador as indicador where indicador.nombre = ?1", Indicador.class)
//				.setParameter(1, nombre)
//				.getResultList();
//		return indicadores;
//	}
	
//	public void guardarIndicadores(List<Indicador> indicadores) {
//		indicadores.stream().forEach(indicador -> guardarIndicador(indicador));
//	}

	public void removerIndicador(Indicador indicador) {
		withTransaction(()->entityManager().remove(indicador));
	}

	@Override
	public Class<Indicador> getTipo() {
		return Indicador.class;
	}

	@Override
	protected <T> String obtenerNombreDe(T t) {
		return ((Indicador)t).getNombre();
	}
}
