package testEscrituraDeIndicadores;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import utils.FilesManager;

public class LecturaIndicadores {
	BaseDeDatos baseDeDatos;
	
	@Before
	public void initialize()  {
		baseDeDatos = new BaseDeDatos("");
		baseDeDatos.setPathIndicadores("./Archivos de prueba/indicadoresPruebaDeEscritura.txt");
	}
	
	@After
	public void borrarArchivo(){
		new FilesManager("./Archivos de prueba/indicadoresPruebaDeEscritura.txt").borrarArchivo();
	}
	
	@Test
	public void escribirUnIndicador() throws IOException {
		baseDeDatos.agregarIndicadorAlArchivo("indicadorEjemploA = ebitda + 500");
		baseDeDatos.leerIndicadores();
		
		Assert.assertTrue(baseDeDatos.existeIndicador("indicadorEjemploA"));
	}
	
	@Test
	public void escribirDosIndicadores() throws IOException {
		baseDeDatos.agregarIndicadorAlArchivo("indicadorEjemploB = ebitda + 500");
		baseDeDatos.agregarIndicadorAlArchivo("indicadorEjemploC = van + ebitda / 2");
		baseDeDatos.leerIndicadores();
		
		Assert.assertTrue(baseDeDatos.existeIndicador("indicadorEjemploB"));
		Assert.assertTrue(baseDeDatos.existeIndicador("indicadorEjemploC"));
	}
	
}