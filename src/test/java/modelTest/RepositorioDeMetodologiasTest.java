package modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dtos.PathFileTxtJson;
import exceptions.EseYaExisteException;
import exceptions.NoSeEncuentraException;
import mockObjects.MockAppData;
import model.repositories.RepositorioDeMetodologias;
import utils.AppData;
import utils.FilesManager;
import utils.JsonCreator;
import model.metodologia.CondicionNoTaxativa;
import model.metodologia.CondicionTaxativa;
import model.metodologia.Metodologia;

public class RepositorioDeMetodologiasTest {
	RepositorioDeMetodologias repositorio = RepositorioDeMetodologias.getInstance();
	List<CondicionTaxativa> condicionesTaxativas = new ArrayList<>();
	List<CondicionNoTaxativa> condicionesNoTaxativas = new ArrayList<>();
	
	Metodologia metodologia1 = new Metodologia("A", condicionesTaxativas, condicionesNoTaxativas);
	Metodologia metodologia2 = new Metodologia("B", condicionesTaxativas, condicionesNoTaxativas);
	Metodologia metodologia3 = new Metodologia("C", condicionesTaxativas, condicionesNoTaxativas);
	Metodologia metodologia4 = new Metodologia("D", condicionesTaxativas, condicionesNoTaxativas);
	
	List<Metodologia> metodologias;

	@Before
	public void setUp() {
		repositorio.setAppData(new MockAppData());
		
		metodologias = new ArrayList<>(Arrays
				.asList(metodologia1, metodologia2, metodologia3, metodologia4));
		
		repositorio.agregarMetodologias(metodologias);
	}

	@After
	public void limpiarRepositorio() {
		repositorio.limpiarRepositorio();
	}
	
	//TODO
	@Test
	public void agregarMetodologiasGeneraArchivo() {
		AppData realAppData = AppData.getInstance();
		String path = "./Archivos de prueba/ArchivoDePruebaParaTestsDeGrabacion.txt";
		FilesManager file = new FilesManager(path);
		repositorio.limpiarRepositorio();
		
		repositorio.setAppData(realAppData);
		realAppData.setInicializacionMetodologias(
				new PathFileTxtJson(path));
		
		repositorio.agregarMetodologias(metodologias);
		String contenidoDelArchivo = file.leerArchivo();
		file.borrarArchivo();
		
		//System.out.println(contenidoDelArchivo);
		
		assertEquals(contenidoDelArchivo, new JsonCreator().getJson(metodologias));
	}


	@Test
	public void filtrarMetodologiasPorNombre() {
		Metodologia metodologia = repositorio.filtrarPorNombre("A");

		assertEquals("A",metodologia.getNombre());
	}
	
	@Test
	public void getMetodologiasOrdenadasPorNombre() {
		repositorio.limpiarRepositorio();
		repositorio.agregarMetodologia(metodologia3);
		repositorio.agregarMetodologia(metodologia1);

		metodologias = repositorio.getOrdenadasPorNombre();

		assertEquals("A",metodologias.get(0).getNombre());
	}
	
	@Test(expected = EseYaExisteException.class)
	public void agregarNombreYaExistenteDaError() {
		Metodologia metodologia = new Metodologia("B", condicionesTaxativas, condicionesNoTaxativas);
		
		repositorio.agregarMetodologia(metodologia);
	}
	
	@Test(expected = NoSeEncuentraException.class)
	public void filtrarPorUnNombreQueNoExisteDaError() {
		repositorio.filtrarPorNombre("404");
	}
}
