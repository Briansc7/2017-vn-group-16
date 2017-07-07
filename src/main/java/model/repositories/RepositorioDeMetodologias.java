package model.repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.EseYaExisteException;
import exceptions.NoSeEncuentraException;
import model.metodologia.Metodologia;
import utils.AppData;

public class RepositorioDeMetodologias {
	private static RepositorioDeMetodologias instance;
	private List<Metodologia> metodologias = new ArrayList<>();;
	
	
	//Singleton
	private RepositorioDeMetodologias(){
	}
	
	public synchronized static RepositorioDeMetodologias getInstance(){
		if(instance == null)
			return new RepositorioDeMetodologias();
		return instance;
	}
	
	public List<Metodologia> getMetodologias(){
		return metodologias;
	}
	
	public void inicializar(List<Metodologia> _metodologias){
		metodologias.addAll(_metodologias);
	}

	public void agregarMetodologias(List<Metodologia> _metodologias) {
		_metodologias.stream().forEach(metodologia -> agregarMetodologia(metodologia));
	}

	public void agregarMetodologia(Metodologia metodologia) {
		comprobarNombre(metodologia);
		metodologias.add(metodologia);
		archivarRepositorio();
	}
	
	//TODO implementar .equals() en metodologia
	private void comprobarNombre(Metodologia metodologia) {
		if(metodologias.contains(metodologia))
				throw new EseYaExisteException("Ya existe una metodologia con el nombre: "
							+ metodologia.getNombre());
	}

	public void removerMetodologia(Metodologia metodologia) {
			metodologias.remove(metodologia);
			archivarRepositorio();
	}
	
	// Filtrar metodologias del repositorio

	public Metodologia filtrarPorNombre(String nombre) {
		List<Metodologia> _metodologias;
		
		_metodologias = metodologias.stream().filter(
				metodologia -> nombre.equals(metodologia.getNombre()))
				.collect(Collectors.toList());
		
		if(_metodologias.isEmpty())
			throw new NoSeEncuentraException("La metodología con nombre: \""
						+ nombre
						+ "\" no se encuentra en el repositprio");

		return _metodologias.get(0);
	}

	// Devuelven una lista ordenada de determinada manera, sin alterar las
	// propias del repositorio

	public List<Metodologia> getOrdenadasPorNombre() {
		List<Metodologia> _metodologias = metodologias.stream()
				.sorted(Comparator.comparing(Metodologia::getNombre))
				.collect(Collectors.toList());
		return _metodologias;
	}
	
	//Utilidades
	
	public int size() {
		return metodologias.size();
	}
	

	public void limpiarRepositorio() {
		metodologias = new ArrayList<Metodologia>();
	}

	public void archivarRepositorio() {
		AppData.getInstance().guardarMetodologias(metodologias);
	}
}
