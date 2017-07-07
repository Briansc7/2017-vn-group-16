package model.repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.EseYaExisteException;
import exceptions.NoSeEncuentraException;
import mockObjects.AppDataI;
import model.metodologia.Metodologia;
import utils.AppData;

public class RepositorioDeMetodologias {
	private static RepositorioDeMetodologias instance;
	private List<Metodologia> metodologias = new ArrayList<Metodologia>();
	private AppDataI appData = AppData.getInstance();
	
	
	//Singleton
	private RepositorioDeMetodologias(){
	}
	
	public synchronized static RepositorioDeMetodologias getInstance(){
		if(instance == null)
			return new RepositorioDeMetodologias();
		return instance;
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

	public void removerMetodologia(Metodologia metodologia) {
			metodologias.remove(metodologia);
			archivarRepositorio();
	}
	
	// Filtrar metodologias del repositorio
	public List<Metodologia> filtrarPorNombre(String nombre) {
		List<Metodologia> _metodologias;
		
		_metodologias = metodologias.stream().filter(
				metodologia -> metodologia.getNombre().contains(nombre))
				.collect(Collectors.toList());
		
		if(_metodologias.isEmpty())
			throw new NoSeEncuentraException("La metodolog�a con nombre: \""
						+ nombre
						+ "\" no se encuentra en el repositprio");

		return _metodologias;
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

	private void archivarRepositorio() {
		appData.guardarMetodologias(metodologias);
	}
	
	//Setters y getters
	public List<Metodologia> getMetodologias(){
		return metodologias;
	}
	
	public void setAppData(AppDataI _appData){
		appData = _appData;
	}
	
	private void comprobarNombre(Metodologia metodologia) {
		if(metodologias.contains(metodologia))
			throw new EseYaExisteException("Ya existe una metodologia con el nombre: "
					+ metodologia.getNombre());
	}
}