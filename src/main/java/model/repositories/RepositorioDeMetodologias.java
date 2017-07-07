package model.repositories;

import java.util.ArrayList;
import java.util.List;

import model.metodologia.Metodologia;

public class RepositorioDeMetodologias {
	private RepositorioDeMetodologias instance;
	List<Metodologia> metodologias = new ArrayList<>();;
	
	private RepositorioDeMetodologias(){
	}
	
	public RepositorioDeMetodologias getInstance(){
		if(instance == null)
			return new RepositorioDeMetodologias();
		return instance;
	}
	
	public List<Metodologia> getMetodologias(){
		return metodologias;
	}
}
