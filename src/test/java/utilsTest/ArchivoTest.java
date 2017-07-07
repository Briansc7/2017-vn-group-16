package utilsTest;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import mockObjects.JsonObject;
import utils.Archivo;
import utils.FilesManager;
import utils.JsonReader;

public class ArchivoTest {
	String rutaDelArchivoAEscribir= "./Archivos de prueba/ArchivoDePruebaParaTestsDeGrabacion.txt";
	String rutaDeArchivoMala = "./Ruta Invalida/Necronomicon.txt";
	
	List<JsonObject> jsonObjects = Arrays.asList(new JsonObject("StringUno", 27, 3.14D));
	
	FilesManager file = new FilesManager(rutaDelArchivoAEscribir);
	Archivo archivo = new Archivo(rutaDelArchivoAEscribir);
	
	@After
	public void borrarArchivo(){
		file.borrarArchivo();
	}
	
	@Test
	public void crearElArchivo(){
		archivo.archivarObjetos(jsonObjects);
		
		String jsonEsperado= "[{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,"
				+ "\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]}]";
		
		String contenidoDelArchivo = file.leerArchivo();
		
		//System.out.println(contenidoDelArchivo);
		
		assertEquals(jsonEsperado,contenidoDelArchivo);
		}
	
	@Test
	public void modificarElArchivo(){
		archivo.archivarObjetos(jsonObjects);
		
		List<JsonObject> differentJsonObjects = Arrays.asList(new JsonObject("StringUno", 27, 3.14D),
				new JsonObject("StringTres", 27, 1.77D));
		archivo.archivarObjetos(differentJsonObjects);
		
		String jsonEsperado= "[{\"artibutoUno\":\"StringUno\",\"artibutoDos\":27,"
				+ "\"artibutoTres\":3.14,\"lista\":[1,2,3,4,5,6,7]},{\"artibutoUno\""
				+ ":\"StringTres\",\"artibutoDos\":27,\"artibutoTres\":1.77,\"lista\":[1,2,3,4,5,6,7]}]";
		
		String contenidoDelArchivo = file.leerArchivo();
		
		//System.out.println(contenidoDelArchivo);
		
		assertEquals(jsonEsperado,contenidoDelArchivo);
	}
	
	@Test
	public void testIntgralConJsonReader(){
		archivo.archivarObjetos(jsonObjects);
		
		String contenidoDelArchivo = file.leerArchivo();
		
		Type jsonObjectType = new TypeToken<List<JsonObject>>() {}.getType();
		
		List<JsonObject> objetosDelArchivo = new JsonReader<JsonObject>(jsonObjectType).obtenerObjetos(contenidoDelArchivo);
		
		assertEquals(objetosDelArchivo.get(0).getArtibutoUno(),
				jsonObjects.get(0).getArtibutoUno());
	}
}
