package utilsTest;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import exceptions.RutaDeArchivoInvalidaException;
import utils.FilesManager;

//No cambiar los archivos para test ya que los tests van a fallar

public class FilesManagerTest {
	String rutaDelArchivoAEscribir = "./Archivos de prueba/ArchivoDeGrabacion.txt";
	String rutaDeArchivoMala = "./Ruta Invalida/Necronomicon.txt";
	String rutaDelArchivoBueno = "./Archivos de prueba/ArchivoGenericoParaLeer.txt";
	String rutaDeArchivoInexistente = "./Archivos de prueba/Necronomicon.txt";

	String jsonMagico = "Un Json Magico";

	FilesManager archivoAEscribir = new FilesManager(rutaDelArchivoAEscribir);

	@After
	public void borrarArchivo() {
		archivoAEscribir.borrarArchivo();
	}

	@Test
	public void sobreescribirCreaArchivoSiNoExiste() {
		archivoAEscribir.sobreescribirArchivo(jsonMagico);

		String contenidoDelArchivo = archivoAEscribir.leerArchivo();

		assertTrue(contenidoDelArchivo.equals(jsonMagico));
	}

	@Test
	public void sobreescribirUnArchivo() {
		archivoAEscribir.sobreescribirArchivo("cualquier cosa a sobreescribir");
		archivoAEscribir.sobreescribirArchivo(jsonMagico);

		String contenidoDelArchivo = archivoAEscribir.leerArchivo();

		assertTrue(contenidoDelArchivo.equals(jsonMagico));
	}

	@Test
	public void leerArchivoBueno() {
		String contenidoDelArchivo = new FilesManager(rutaDelArchivoBueno).leerArchivo();
		String contenidoEsperado = "Este es un archivo para probar la lectura de los archivos";

		assertTrue(contenidoDelArchivo.equals(contenidoEsperado));
	}

	@Test(expected = RutaDeArchivoInvalidaException.class)
	public void rutaDeArchivoMalaTiraExeptionEnSobreescribir() {

		new FilesManager(rutaDeArchivoMala).sobreescribirArchivo(jsonMagico);
	}

	@Test(expected = RutaDeArchivoInvalidaException.class)
	public void fallarAlLeerArchivoIxensistente() {
		new FilesManager(rutaDeArchivoInexistente).leerArchivo();
	}
}