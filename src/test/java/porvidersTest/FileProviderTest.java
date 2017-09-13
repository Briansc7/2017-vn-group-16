package porvidersTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dtos.PathFile;
import dtos.PathFileTxtJson;
import model.Metodologia;
import model.funciones.CondicionNoTaxativa;
import model.funciones.CondicionTaxativa;
import providers.FileProvider;
import utils.Archivo;
import utils.FilesManager;

public class FileProviderTest {
	FileProvider provider = new FileProvider();
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
	
	@Before
	public void setUp() {
		archivo.archivarObjetos(metodologias);
	}
	
	@After
	public void borrarArchivo() {
		file.borrarArchivo();
	}

	@Test
	public void getInformationMetodologia() {
		List<Metodologia> metodologiasProvider = provider.getInformationMetodologia(dto);
		//assertEquals(metodologiasProvider, metodologias);
	}
	
	@Test
	public void siElArchivoNoExisteCreaElArchivo() {
		file.borrarArchivo();
		provider.getInformationMetodologia(dto);
		
		assertEquals(file.leerArchivo(),"");
	}
	
	@Test
	public void siElArchivoNoExisteReotrnaListaVacia() {
		file.borrarArchivo();
		List<Metodologia> metodologiasProvider = provider.getInformationMetodologia(dto);
		
		assertTrue(metodologiasProvider.isEmpty());
	}
}
