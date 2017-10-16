package modelTest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.EseYaExisteException;
import model.Indicador;
import model.repositories.RepositorioDeIndicadores;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import parser.ParseException;
import parser.TokenMgrError;

public class RepositorioDeIndicadoresTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	private RepositorioDeIndicadores repositorio = new RepositorioDeIndicadores();
	private static boolean setUpIsDone = false;
	
	private Indicador indicador1;
	private Indicador indicador2;
	private Indicador indicador3;
	private List<Indicador> indicadores;
	
	@Before
	public void setUp() throws ParseException, TokenMgrError{
		if(setUpIsDone)
			return;
		indicador1 = new Indicador("Indicador del repositorio 1","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");
		indicador2 = new Indicador("Indicador del repositorio 2","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");
		indicador3 = new Indicador("Indicador del repositorio 3","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");
		
		indicadores = Arrays.asList(indicador1, indicador2, indicador3);
		guardarIndicadores();
		
		setUpIsDone=true;
	}
	
	public void guardarIndicadores(){
		repositorio.guardarIndicadores(indicadores);
	}
	
	@Test
	public void obtenerIndicador(){
		Indicador indicadorObtenido = repositorio.obtenerIndicador("Indicador del repositorio 1");
		
		indicadorObtenido.getFormula();
		indicadorObtenido.getNombre();
	}
	
	@Test
	public void obtenerIndicadores(){
		List<Indicador> indicadorsObtenidas = repositorio.obtenerIndicadores();
		
		assertEquals(3, indicadorsObtenidas.size());
	}
	
	@Test
	public void obtenerIndicadorPorNombre(){
		Indicador indicadorObtenida = repositorio.obtenerIndicador("Indicador del repositorio 1");
		
		assertEquals("Indicador del repositorio 1", indicadorObtenida.getNombre());
	}
	
	@Test(expected = EseYaExisteException.class)
	public void guardarIndicadorQueYaExisteTiraExepcion(){
		repositorio.guardarIndicador(indicador1);
	}
}
