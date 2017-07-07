package providers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import utils.JsonReader;
import utils.FilesManager;
import dtos.PathFile;
import exceptions.RutaDeArchivoInvalidaException;
import model.metodologia.Metodologia;

public class FileProvider implements MetodologiaProvider {

	public List<Metodologia> getInformationMetodologia(PathFile datosDeCarga) {
		String rutaDelArchivo = datosDeCarga.getPathFile();
		FilesManager file = new FilesManager(rutaDelArchivo);
		String json;
		
		try{
			json = file.leerArchivo();
		}catch(RutaDeArchivoInvalidaException e){
			file.sobreescribirArchivo("");
			return new ArrayList<Metodologia>();
		}

		Type tipoLista = new TypeToken<List<Metodologia>>() {}.getType();

		List<Metodologia> metodologias = new JsonReader<Metodologia>(tipoLista).obtenerObjetos(json);

		return metodologias;
	}
}