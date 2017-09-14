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
		Indicador indicadorObtenido = repositorio.obtenerIndicador("Indicador del repositorio");
		
		indicadorObtenido.getFormula();
		indicadorObtenido.getNombre();
	}
}
