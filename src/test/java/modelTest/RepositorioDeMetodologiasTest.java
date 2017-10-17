package modelTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.EseYaExisteException;
import model.repositories.RepositorioDeMetodologias;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import testMetodologia.CondicionBuilder;
import model.Metodologia;
import model.funciones.Longevidad;
import model.metodologia.condiciones.Comparador;
import model.metodologia.condiciones.CondicionGeneral;

public class RepositorioDeMetodologiasTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	private RepositorioDeMetodologias repositorio = RepositorioDeMetodologias.getInstance();
	private static boolean setUpIsDone = false;
	
	private CondicionGeneral longevidadPropia;
	private CondicionGeneral longevidadComparativa;
	private Metodologia metodologia1;
	private Metodologia metodologia2;
	private Metodologia metodologia3;
	private List<Metodologia> metodologias;
	
	@Before
	public void setUp() {
		if(setUpIsDone)
			return;
		longevidadPropia = new CondicionBuilder()
				.periodoDeEvaluacion(1)
				.funcionParaObtenerValor(new Longevidad())
				.comparador(Comparador.MAYOR)
				.valorContraElQueSeCompara(BigDecimal.ONE)
				.build();
		
		longevidadComparativa = new CondicionBuilder()
				.periodoDeEvaluacion(1)
				.funcionParaObtenerValor(new Longevidad())
				.comparador(Comparador.MENOR_O_IGUAL)
				.build();
		
		metodologia1 = new Metodologia("Metodologia del repositorio 1", Arrays.asList(longevidadComparativa, longevidadPropia));
		metodologia2 = new Metodologia("Metodologia del repositorio 2", new ArrayList<CondicionGeneral>());
		metodologia3 = new Metodologia("Metodologia del repositorio 3", new ArrayList<CondicionGeneral>());
		
		metodologias = Arrays.asList(metodologia1, metodologia2, metodologia3);
		guardarMetodologia();
		setUpIsDone=true;
	}
	
	public void guardarMetodologia(){
		repositorio.guardarTodos(metodologias);
	}
	
	@Test
	public void obtenerMetodologia(){
		Metodologia metodologiaObtenida = repositorio.buscarMetodologia("Metodologia del repositorio 1");
		
		metodologiaObtenida.getNombre();
	}
	
	@Test
	public void obtenerMetodologias(){
		List<Metodologia> metodologiasObtenidas = repositorio.buscarTodos();
		
		assertEquals(3, metodologiasObtenidas.size());
	}
	
	@Test
	public void obtenerMetodologiaPorNombre(){
		Metodologia metodologiaObtenida = repositorio.buscarMetodologia("Metodologia del repositorio 1");
		
		assertEquals("Metodologia del repositorio 1", metodologiaObtenida.getNombre());
	}
	
	@Test(expected = EseYaExisteException.class)
	public void guardarMetodologiaQueYaExisteTiraExepcion(){
		repositorio.guardar(metodologia1);
	}
}
