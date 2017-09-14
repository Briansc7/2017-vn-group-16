package modelTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.repositories.RepositorioDeMetodologias;
import testMetodologia.CondicionBuilder;
import model.Metodologia;
import model.funciones.Longevidad;
import model.metodologia.condiciones.Comparador;
import model.metodologia.condiciones.CondicionGeneral;

public class RepositorioDeMetodologiasTest {
	RepositorioDeMetodologias repositorio = RepositorioDeMetodologias.getInstance();
	
	CondicionGeneral longevidadPropia;
	CondicionGeneral longevidadComparativa;
	Metodologia metodologiaDelRepositorio;
	
	@Before
	public void setUp() {
		longevidadPropia = new CondicionBuilder()
				.periodoDeEvaluacion(1)
				.funcionParaObtenerValor(new Longevidad())
				.comparador(Comparador.MAYOR)
				.valorContraElQueSeCompara(BigDecimal.ONE)
				.build();
		
		longevidadComparativa = new CondicionBuilder()
				.periodoDeEvaluacion(1)
				.funcionParaObtenerValor(new Longevidad())
				.comparador(Comparador.MENOROIGUAL)
				.build();
		
		metodologiaDelRepositorio = new Metodologia("Metodologia del repositorio", Arrays.asList(longevidadComparativa, longevidadPropia));
		
		guardarMetodologia();
	}
	
	public void guardarMetodologia(){
		repositorio.guardarMetodologia(metodologiaDelRepositorio);
	}
	
	@Test
	public void obtenerMetodologia(){
		List<Metodologia> metodologias = repositorio.obtenerMetodologias("Metodologia del repositorio");
		
		metodologias.get(0).getNombre();
		
		assertEquals(1, metodologias.size());
	}
}
