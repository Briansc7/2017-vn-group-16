package utils;

import java.util.ArrayList;
import java.util.List;

import dtos.PathFile;
import dtos.PathFileTxtJson;
import mockObjects.AppDataI;
import model.Metodologia;
import model.repositories.RepositorioDeMetodologias;
import providers.FileProvider;
import providers.MetodologiaProvider;

public class AppData implements AppDataI{
	private static AppData instance;
	private List<MetodologiaProvider> providersMetodologia = new ArrayList<MetodologiaProvider>();
	private PathFile inicializacionMetodologias = new PathFileTxtJson("./Archivos del sistema/Metodologias.txt");

	// Singleton
	private AppData() {
		providersMetodologia.add(new FileProvider());
	}

	public synchronized static AppData getInstance() {
		if (instance == null)
			return new AppData();
		return instance;
	}

	public void guardarMetodologias(List<Metodologia> metodologias) {
		Archivo archivo = new Archivo(inicializacionMetodologias.getPathFile());
		archivo.archivarObjetos(metodologias);
	}

	// Setters y getters
	public List<MetodologiaProvider> getProvidersMetodologia() {
		return providersMetodologia;
	}

	public AppData setInicializacionMetodologias(PathFile _inicializacionMetodologias) {
		inicializacionMetodologias = _inicializacionMetodologias;
		return this;
	}

	public AppData setProvidersMetodologia(List<MetodologiaProvider> providersMetodologia) {
		this.providersMetodologia = providersMetodologia;
		return this;
	}

	@Override
	public void inicializarMetodologias() {
		// TODO Auto-generated method stub
		
	}
}
