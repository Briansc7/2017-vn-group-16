package testEscrituraDeIndicadores;

/*
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BaseDeDatos;
import model.Planilla;

public class LecturaIndicadores {
	BaseDeDatos baseDeDatos;
	
	@Before
	public void initialize()  {
		baseDeDatos = new BaseDeDatos("");
		baseDeDatos.setPathIndicadores("./Archivos de prueba/indicadoresDePrueba.txt");
		Planilla.instance.setPathIndicadores("./Archivos de prueba/indicadoresDePrueba.txt");
	}
	
	@Test
	public void escribirUnIndicador() throws IOException {
		Planilla.instance.agregarIndicadorAlArchivo("indicadorEjemploA = c.ebitda + 500");
		baseDeDatos.leerIndicadores();
		
		Assert.assertTrue(Planilla.instance.existeIndicador("indicadorEjemploA"));
	}
	
	@Test
	public void escribirDosIndicadores() throws IOException {
		Planilla.instance.agregarIndicadorAlArchivo("indicadorEjemploB = c.ebitda + 500");
		Planilla.instance.agregarIndicadorAlArchivo("indicadorEjemploC = c.van + c.ebitda / 2");
		baseDeDatos.leerIndicadores();
		
		Assert.assertTrue(Planilla.instance.existeIndicador("indicadorEjemploB"));
		Assert.assertTrue(Planilla.instance.existeIndicador("indicadorEjemploC"));
	}
	
}
*/