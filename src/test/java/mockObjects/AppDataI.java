package mockObjects;

import java.util.List;

import dtos.PathFile;
import model.metodologia.Metodologia;
import providers.MetodologiaProvider;

public interface AppDataI {
	
	public void guardarMetodologias(List<Metodologia> metodologias);
	

	public void inicializarMetodologias();
	
	public List<MetodologiaProvider> getProvidersMetodologia();
	
	public AppDataI setInicializacionMetodologias(PathFile inicializacionMetodologias);

	public AppDataI setProvidersMetodologia(List<MetodologiaProvider> providersMetodologia);
}
