package providers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import dtos.PathFile;
import exceptions.RutaDeArchivoInvalidaException;
import model.Metodologia;
import utils.FilesManager;
import utils.JsonReader;

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
	public static void main(String... args){
		/*FileProvider provider = new FileProvider();
		String path = "./Archivos de prueba/ArchivoDePruebaParaTestsDeGrabacion.txt";
		PathFile dto = new PathFileTxtJson(path);
		FilesManager file = new FilesManager(path);
		Archivo archivo = new Archivo(path);
		
		List<CondicionTaxativa> condicionesTaxativas = new ArrayList<>();
		List<CondicionNoTaxativa> condicionesNoTaxativas = new ArrayList<>();
		
		Metodologia metodologia1 = new Metodologia("A", condicionesTaxativas, condicionesNoTaxativas);
		Metodologia metodologia2 = new Metodologia("B", condicionesTaxativas, condicionesNoTaxativas);
		Metodologia metodologia3 = new Metodologia("C", condicionesTaxativas, condicionesNoTaxativas);
		Metodologia metodologia4 = new Metodologia("D", condicionesTaxativas, condicionesNoTaxativas);
		
		List<Metodologia> metodologias = new ArrayList<>(Arrays
				.asList(metodologia1, metodologia2, metodologia3, metodologia4));
		
		archivo.archivarObjetos(metodologias);
		
		System.out.println(file.leerArchivo());
		
		List<Metodologia> metodologiasProvider = provider.getInformationMetodologia(dto);*/
	}
}