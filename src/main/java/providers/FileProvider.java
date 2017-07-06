package providers;

import java.util.List;
import utils.JsonReader;
import utils.FilesManager;
import dtos.PathFile;
import model.metodologia.Metodologia;

public class FileProvider implements MetodologiaProvider {

	public List<Metodologia> getInformationMetodologia(PathFile datosDeCarga) {
		String rutaDelArchivo = datosDeCarga.getPathFile();
		
		String json = new FilesManager(rutaDelArchivo).leerArchivo();
		
		List<Metodologia> metodologias = new JsonReader().obtenerMetodologias(json);

		return metodologias;
	}
}
