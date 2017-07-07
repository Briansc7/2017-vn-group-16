package porvidersTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import providers.FileProvider;

public class FileProviderTest {
	FileProvider provider = new FileProvider();
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void borrarArchivo() {
		
	}

	@Test
	public void getInformationMetodologia() {
		provider.getInformationMetodologia(datosDeCarga)
	}
}
