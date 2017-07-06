package mockObjects;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonObject {
	private String artibutoUno;
	private Integer artibutoDos;
	private double artibutoTres;
	List<Integer> lista = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
	
	public JsonObject(String artibutoUno, Integer artibutoDos, double artibutoTres) {
		super();
		this.artibutoUno = artibutoUno;
		this.artibutoDos = artibutoDos;
		this.artibutoTres = artibutoTres;
	}
	
	//Setters y getters
	public String getArtibutoUno() {
		return artibutoUno;
	}

	public void setArtibutoUno(String artibutoUno) {
		this.artibutoUno = artibutoUno;
	}

	public Integer getArtibutoDos() {
		return artibutoDos;
	}

	public void setArtibutoDos(Integer artibutoDos) {
		this.artibutoDos = artibutoDos;
	}

	public double getArtibutoTres() {
		return artibutoTres;
	}

	public void setArtibutoTres(double artibutoTres) {
		this.artibutoTres = artibutoTres;
	}

	public List<Integer> getLista() {
		return lista;
	}

	public void setLista(List<Integer> lista) {
		this.lista = lista;
	}
}
