package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Planilla {

	public static Planilla instance = new Planilla();
	//private static Empresa empresaElegida;
	//private static Integer periodoElegido;

	
	private List<Indicador> indicadores = new ArrayList<>();
	
	/*public Planilla(List<Indicador> indicadores){
		this.indicadores.addAll(indicadores);
	}*/
	public Boolean existeIndicador(String nombre) {
		return this.primero(nombre).isPresent();
	}

	public Optional<Indicador> primero(String nombre) {
		return this.indicadores.stream().filter(indicador -> indicador.getNombre().equalsIgnoreCase(nombre)).findFirst();
	}
	
	public Indicador buscarIndicador(String nombre) {
		if(!this.existeIndicador(nombre)){
			throw new RuntimeException("No existe el indicador");
		}
		return this.primero(nombre).get();
	}
	
	public void agregarIndicador(Indicador indicador){
		this.indicadores.add(indicador);
	}
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
}
