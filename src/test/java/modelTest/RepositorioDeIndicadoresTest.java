package modelTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Indicador;
import model.repositories.RepositorioDeIndicadores;
import parser.ParseException;
import parser.TokenMgrError;

public class RepositorioDeIndicadoresTest {
	RepositorioDeIndicadores repositorio = new RepositorioDeIndicadores();
	Indicador indicadorDelRepositorio;
	
	@Before
	public void setUp() throws ParseException, TokenMgrError{
		indicadorDelRepositorio = new Indicador("Indicador del repositorio","ingresoNeto/2*2 + capitalTotal/2*2 - capitalTotal*ingresoNeto");
		guardarIndicador();
	}
	
	public void guardarIndicador(){
		repositorio.guardarIndicador(indicadorDelRepositorio);
	}
	
	@Test
	public void obtenerIndicador(){
		List<Indicador> indicadores = repositorio.obtenerIndicadores("Indicador del repositorio");
		
		indicadores.get(0).getFormula();
		indicadores.get(0).getNombre();
		
		assertEquals(1, indicadores.size());
	}
}
