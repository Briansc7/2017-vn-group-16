package mockObjects;

import java.util.ArrayList;
import java.util.List;

import dtos.PathFile;
import model.metodologia.Metodologia;
import providers.MetodologiaProvider;

public class MockAppData implements AppDataI{
		public void guardarMetodologias(List<Metodologia> metodologias) {
		}

		public void inicializarMetodologias() {
		}

		// Setters y getters
		public List<MetodologiaProvider> getProvidersMetodologia() {
			return new ArrayList<MetodologiaProvider>();
		}

		public AppDataI setInicializacionMetodologias(PathFile _inicializacionMetodologias) {
			return this;
		}

		public AppDataI setProvidersMetodologia(List<MetodologiaProvider> providersMetodologia) {
			return this;
		}
}
